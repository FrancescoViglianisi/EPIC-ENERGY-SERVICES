package it.be.energy.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import it.be.energy.exceptions.FatturaException;
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
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
	
	public Page<Fattura> findByClienteRagioneSocialeLike(Pageable pageable, String nome) {
		return fatturaRepository.findByClienteRagioneSocialeLike(pageable, nome);
	}

	public Page<Fattura> findByStato(Pageable pageable, StatoFattura stato) {
		return fatturaRepository.findByStatoFattura(pageable, stato);
	}

	public Page<Fattura> findByData(Pageable pageable, Date data) {
		return fatturaRepository.findByData(pageable, data);
	}

	public Page<Fattura> findByAnno(Pageable pageable, Integer anno) {
		return fatturaRepository.findByAnno(pageable, anno);
	}

	public Page<Fattura> findByImportoBetween(Pageable pageable, BigDecimal minimo, BigDecimal massimo) {
		return fatturaRepository.findByImportoBetween(pageable, minimo, massimo);
	}
}
