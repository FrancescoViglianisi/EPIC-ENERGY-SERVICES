package it.be.energy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exceptions.FatturaException;
import it.be.energy.model.StatoFattura;
import it.be.energy.repository.StatoFatturaRepository;
@Service
public class StatoFatturaService {
	
	@Autowired
	StatoFatturaRepository statoFatturaRepository;
	
	public Page<StatoFattura> findAll(Pageable pageable) {
		return statoFatturaRepository.findAll(pageable);
	}
	public StatoFattura findById(Long id) {
		Optional<StatoFattura> statoFatturaTrovata = statoFatturaRepository.findById(id);
		if (statoFatturaTrovata.isPresent()) {
			return statoFatturaTrovata.get();
		} else {
			throw new FatturaException("Fattura non trovata!");
		}
	}

	public StatoFattura insert(StatoFattura stato) {
		return statoFatturaRepository.save(stato);
	}

	public void delete(Long id) {
		Optional<StatoFattura> statoFatturaEliminata = statoFatturaRepository.findById(id);
		if (statoFatturaEliminata.isPresent()) {
			statoFatturaRepository.deleteById(id);
		} else {
			throw new FatturaException("Fattura non trovata!");
		}
	}

	public StatoFattura update(StatoFattura stato, Long id) {
		Optional<StatoFattura> statoFatturaDaAggiornare = statoFatturaRepository.findById(id);
		if (statoFatturaDaAggiornare.isPresent()) {
			StatoFattura statoFatturaAggiornata = statoFatturaDaAggiornare.get();
			
			statoFatturaAggiornata.setNome(stato.getNome());
			return statoFatturaRepository.save(statoFatturaAggiornata);
		} else {
			throw new FatturaException("Fattura non trovata!");
		}
	}

}
