package it.be.energy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exceptions.ClienteException;
import it.be.energy.model.Provincia;
import it.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;

	public Page<Provincia> findAll(Pageable pageable) {
		return provinciaRepository.findAll(pageable);
	}

	public Provincia findById(Long id) {
		Optional<Provincia> find = provinciaRepository.findById(id);
		if (find.isPresent()) {
			return find.get();
		} else {
			throw new ClienteException("Nessuna provincia con questo id");
		}

	}

}
