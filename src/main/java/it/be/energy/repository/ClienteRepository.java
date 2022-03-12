package it.be.energy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Page<Cliente> gerAll(Pageable pageable);
	
	public Page<Cliente> getAllOrderByRagioneSocialeAsc(Pageable pageable);
	
	public Page<Cliente> getAllOrderByFatturatoAnnualeDesc(Pageable pageable);
	
	public Page<Cliente> getAllOrderByDataInserimento(Pageable pageable);
	
	public Page<Cliente> getAllOrderByDataUltimoContatto(Pageable pageable);
	
}
