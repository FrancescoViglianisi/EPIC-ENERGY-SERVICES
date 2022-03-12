package it.be.energy.services;

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

	public Cliente update(Cliente cliente) {
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

	public Cliente getById(Long id) {
		Optional<Cliente> find = clienteRepository.findById(id);
		if (find.isPresent()) {
			return find.get();
		} else {
			throw new ClienteException("Nessun cliente con questo id");
		}

	}

	public Page<Cliente> getClientiOrderByName(Pageable pageable) {
		return clienteRepository.getAllOrderByRagioneSocialeAsc(pageable);
	}
	
	public Page<Cliente> getAllOrderByFatturatoAnnuale(Pageable pageable) {
		return clienteRepository.getAllOrderByFatturatoAnnualeDesc(pageable);
	}
	
	public Page<Cliente> getAllOrderByDataInserimento(Pageable pageable) {
		return clienteRepository.getAllOrderByDataInserimento(pageable);
	}
	
	public Page<Cliente> getAllOrderByDataUltimoContatto(Pageable pageable) {
		return clienteRepository.getAllOrderByDataUltimoContatto(pageable);
		
	}
}
