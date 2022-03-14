package it.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.exceptions.StatoFatturaException;
import it.be.energy.model.StatoFattura;
import it.be.energy.services.StatoFatturaService;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/stato_fattura")
@Slf4j
@SecurityRequirement(name = "bearerAuth")

public class StatoFatturaController {
	
	@Autowired
	StatoFatturaService statoFatturaService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovatutti")
	@Operation(summary = "Visualizza tutti i clienti", description = "")
	public ResponseEntity<Page<StatoFattura>> findAll(Pageable pageable) {
		log.info("*** Inizio ricerca clienti ***");
		Page<StatoFattura> trovaTutti = statoFatturaService.findAll(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** statoFattura trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** statoFattura non trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperid/{id}")
	@Operation(summary = "Visualizza tutti i clienti per id", description = "")
	public ResponseEntity<StatoFattura> trovaById(@PathVariable Long id) {
		log.info("*** Inizio Ricerca per id ***");
		StatoFattura trovaById = statoFatturaService.findById(id);
		log.info("*** Fine ricerca per id ***");
		return new ResponseEntity<>(trovaById, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/inserisci")
	@Operation(summary = "Inserisce un statoFattura", description = "")
	public ResponseEntity<StatoFattura> insert(@RequestBody StatoFattura stato) {
		log.info("*** Inizio inserimento statoFattura ***");
		StatoFattura c = statoFatturaService.insert(stato);
		log.info("*** statoFattura salvato ***");
		return new ResponseEntity<>(c, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/aggiorna")
	@Operation(summary = "Aggiorna un statoFattura tramite il suo id", description = "")
	public ResponseEntity<StatoFattura> update(@RequestBody StatoFattura stato, @PathVariable Long id)
			throws StatoFatturaException {
		log.info("*** Inizio aggornamento statoFattura ***");
		StatoFattura c = statoFatturaService.update(stato, id);
		log.info("*** Fine aggiornamento statoFattura ***");
		return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/cancella/{id}")
	@Operation(summary = "Cancella un statoFattura tramite il suo id", description = "")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("*** Inizio cancellazione statoFattura ***");
		statoFatturaService.delete(id);
		log.info("*** Fine cancellazione statoFattura ***");
		return new ResponseEntity<>("statoFattura cancellato correttamente!", HttpStatus.OK);
	}


}
