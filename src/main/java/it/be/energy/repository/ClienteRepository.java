package it.be.energy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.be.energy.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Page<Cliente> findByOrderByRagioneSocialeAsc(Pageable pageable);
	
	public Page<Cliente> findByOrderByFatturatoAnnualeDesc(Pageable pageable);
	
	public Page<Cliente> findByOrderByDataInserimento(Pageable pageable);
	
	public Page<Cliente> findByOrderByDataUltimoContatto(Pageable pageable);
	
	public Page<Cliente> findByOrderBySedeLegaleComuneProvincia(Pageable pageable);
	
}
