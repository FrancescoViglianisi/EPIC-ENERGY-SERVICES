package it.be.energy.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exceptions.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente insertCliente(Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}

	public void deleteClienteById(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente update(Cliente cliente ,Long id) {
		Optional<Cliente> update = clienteRepository.findById(cliente.getId());
		if (update.isPresent()) {
			Cliente clienteUpdate = update.get();
			clienteUpdate.setCognomeContatto(cliente.getCognomeContatto());
			clienteUpdate.setDataInserimento(cliente.getDataInserimento());
			clienteUpdate.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteUpdate.setEMail(cliente.getEMail());
			clienteUpdate.setEMailContatto(cliente.getEMailContatto());
			clienteUpdate.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteUpdate.setFatture(cliente.getFatture());
			clienteUpdate.setNomeContatto(cliente.getNomeContatto());
			clienteUpdate.setPartitaIva(cliente.getPartitaIva());
			clienteUpdate.setPec(cliente.getPec());
			clienteUpdate.setRagioneSociale(cliente.getRagioneSociale());
			clienteUpdate.setSedeLegale(cliente.getSedeLegale());
			clienteUpdate.setSedeOperativa(cliente.getSedeOperativa());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setTelefonoContatto(cliente.getTelefonoContatto());
			clienteUpdate.setTipoCliente(cliente.getTipoCliente());

			clienteRepository.save(clienteUpdate);
			return clienteUpdate;
		} else {
			throw new ClienteException("Cliente non modificato");
		}

	}

	public Page<Cliente> getAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Cliente findById(Long id) {
		Optional<Cliente> find = clienteRepository.findById(id);
		if (find.isPresent()) {
			return find.get();
		} else {
			throw new ClienteException("Nessun cliente con questo id");
		}

	}

	public Page<Cliente> findByOrderByClientiOrderByName(Pageable pageable) {
		return clienteRepository.findAllByOrderByRagioneSocialeAsc(pageable);
	}
	
	
	public Page<Cliente> findByOrderByDataInserimento(Pageable pageable) {
		return clienteRepository.findAllByOrderByDataInserimento(pageable);
	}
	
	public Page<Cliente> findByOrderByDataUltimoContatto(Pageable pageable) {
		return clienteRepository.findAllByOrderByDataUltimoContatto(pageable);
		
	}
	
	public Page<Cliente> findByOrderBySedeLegaleComuneProvincia(Pageable pageable) {
		return clienteRepository.findAllByOrderBySedeLegaleComuneProvincia(pageable);
	}
	
	 public Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(Pageable pageable, BigDecimal fatturatoAnnuale) {
		 return clienteRepository.findByFatturatoAnnualeGreaterThanEqual(pageable, fatturatoAnnuale);
	 }
	 
	 public Page<Cliente> findByFatturatoAnnualeLessThanEqual(Pageable pageable, BigDecimal fatturatoAnnuale) {
		 return clienteRepository.findByFatturatoAnnualeLessThanEqual(pageable, fatturatoAnnuale);
	 }
	 
	 public Page<Cliente> findByFatturatoAnnualeBetween(Pageable pageable, BigDecimal fatturatoMin, BigDecimal fatturatoMax) {
		 return clienteRepository.findByFatturatoAnnualeBetween(pageable, fatturatoMin, fatturatoMax);
	 }
	 
	 public Page<Cliente> findByDataInserimentoGreaterThanEqual(Pageable pageable, LocalDate dataInserimento) {
		 return clienteRepository.findByDataInserimentoGreaterThanEqual(pageable, dataInserimento);
	 }
	 
	 public Page<Cliente> findByDataInserimentoLessThanEqual(Pageable pageable, LocalDate dataInserimento) {
		 return clienteRepository.findByDataInserimentoLessThanEqual(pageable, dataInserimento);
	 }
	 
	 public Page<Cliente> findByDataInserimentoBetween(Pageable pageable, LocalDate dataMin, LocalDate dataMax) {
		 return clienteRepository.findByDataInserimentoBetween(pageable, dataMin, dataMax);
	 }

	    public Page<Cliente> findByDataUltimoContattoGreaterThanEqual(Pageable pageable, LocalDate dataUltimoContatto) {
	    	return clienteRepository.findByDataUltimoContattoGreaterThanEqual(pageable, dataUltimoContatto);
	    }

	    public Page<Cliente> findByDataUltimoContattoLessThanEqual(Pageable pageable, LocalDate dataUltimoContatto) {
	    	return clienteRepository.findByDataUltimoContattoLessThanEqual(pageable, dataUltimoContatto);
	    }

	    public Page<Cliente> findByDataUltimoContattoBetween(Pageable pageable, LocalDate dataUltimoContattoMin, LocalDate dataUltimoContattoMax) {
	    	return clienteRepository.findByDataUltimoContattoBetween(pageable, dataUltimoContattoMin, dataUltimoContattoMax);
	    }

	    public Page<Cliente> findByRagioneSocialeContaining(Pageable pageable, String ragioneSociale) {
	    	return clienteRepository.findByRagioneSocialeContaining(pageable, ragioneSociale);
	    }

		public Page<Cliente> findAllByOrderByFatturatoAnnuale(Pageable pageable) {
			return clienteRepository.findAllByOrderByFatturatoAnnuale(pageable);
		}

		public Page<Cliente> findAllByOrderByRagioneSocialeAsc(Pageable pageable) {
			return clienteRepository.findAllByOrderByRagioneSocialeAsc(pageable);
		}
}
