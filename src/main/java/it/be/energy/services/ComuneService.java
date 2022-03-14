package it.be.energy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exceptions.ClienteException;
import it.be.energy.model.Comune;
import it.be.energy.repository.ComuneRepository;
@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	
	
	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepository.findAll(pageable);
	}

	public Comune findById(Long id) {
		Optional<Comune> find = comuneRepository.findById(id);
		if (find.isPresent()) {
			return find.get();
		} else {
			throw new ClienteException("Nessuna provincia con questo id");
		}

	}

	
}
