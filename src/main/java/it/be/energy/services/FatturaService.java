package it.be.energy.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.be.energy.exceptions.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;

	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepository.findAll(pageable);
	}

	public Fattura findById(Long id) {
		Optional<Fattura> fatturaTrovata = fatturaRepository.findById(id);
		if (fatturaTrovata.isPresent()) {
			return fatturaTrovata.get();
		} else {
			throw new FatturaException("Fattura non trovata!");
		}
	}

	public Fattura insert(Fattura fattura) {
		return fatturaRepository.save(fattura);
	}

	public void delete(Long id) {
		Optional<Fattura> fatturaEliminata = fatturaRepository.findById(id);
		if (fatturaEliminata.isPresent()) {
			fatturaRepository.deleteById(id);
		} else {
			throw new FatturaException("Fattura non trovata!");
		}
	}

	public Fattura update(Fattura fattura, Long id) {
		Optional<Fattura> fatturadaAggiornare = fatturaRepository.findById(id);
		if (fatturadaAggiornare.isPresent()) {
			Fattura fatturaAggiornata = fatturadaAggiornare.get();
			fatturaAggiornata.setNumeroFattura(fattura.getNumeroFattura());
			fatturaAggiornata.setAnno(fattura.getAnno());
			fatturaAggiornata.setCliente(fattura.getCliente());
			fatturaAggiornata.setData(fattura.getData());
			fatturaAggiornata.setImporto(fattura.getImporto());
			fatturaAggiornata.setStatoFattura(fattura.getStatoFattura());
			return fatturaRepository.save(fatturaAggiornata);
		} else {
			throw new FatturaException("Fattura non trovata!");
		}
	}
	
	public Page<Fattura> findByClienteRagioneSocialeContaining(Pageable pageable, String nome) {
		return fatturaRepository.findByClienteRagioneSocialeContaining(pageable, nome);
	}

	public Page<Fattura> findByStato(Pageable pageable, Long id) {
		return fatturaRepository.findByStatoFatturaId(pageable, id);
	}

	public Page<Fattura> findByData(Pageable pageable, LocalDate data) {
		return fatturaRepository.findByData(pageable, data);
	}

	public Page<Fattura> findByAnno(Pageable pageable, Integer anno) {
		return fatturaRepository.findByAnno(pageable, anno);
	}

	public Page<Fattura> findByImportoBetween(Pageable pageable, BigDecimal minimo, BigDecimal massimo) {
		return fatturaRepository.findByImportoBetween(pageable, minimo, massimo);
	}
}
