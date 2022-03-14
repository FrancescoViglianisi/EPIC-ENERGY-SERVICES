package it.be.energy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exceptions.IndirizzoException;
import it.be.energy.model.Indirizzo;
import it.be.energy.repository.IndirizzoRepository;
@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
	

	public Page<Indirizzo> findAll(Pageable pageable) {
		 return indirizzoRepository.findAll(pageable);
	}
	
	public Indirizzo findById(Long id) {
		Optional<Indirizzo> indirizzoTrovata = indirizzoRepository.findById(id);
		if(indirizzoTrovata.isPresent()) {
			return indirizzoTrovata.get();
		} else {
			throw new IndirizzoException("indirizzo non trovato!");
		}
	}
	
	public Indirizzo insert(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);
	}
	
	public void delete(Long id) {
		Optional<Indirizzo> indirizzoEliminata = indirizzoRepository.findById(id);
		if(indirizzoEliminata.isPresent()) {
			indirizzoRepository.deleteById(id);
		} else {
			throw new IndirizzoException("indirizzo non trovato!");
		}
	}
	
	public Indirizzo update (Indirizzo indirizzo , Long id) {
		Optional<Indirizzo> indirizzodaAggiornare = indirizzoRepository.findById(id);
		if(indirizzodaAggiornare.isPresent()) {
			Indirizzo indirizzoAggiornata = indirizzodaAggiornare.get();
			indirizzoAggiornata.setVia(indirizzo.getVia());
			indirizzoAggiornata.setCivico(indirizzo.getCivico());
			indirizzoAggiornata.setLocalita(indirizzo.getLocalita());
			indirizzoAggiornata.setCap(indirizzo.getCap());
			indirizzoAggiornata.setComune(indirizzo.getComune());
			return indirizzoRepository.save(indirizzoAggiornata);
		} else {
			throw new IndirizzoException("indirizzo non trovato!");
		}
	}
}
