package it.be.energy.controller; 
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import it.be.energy.exceptions.ClienteException;
import it.be.energy.model.Cliente;
import it.be.energy.services.ClienteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cliente")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovatutti")
	@Operation(summary = "Visualizza tutti i clienti", description = "")
	public ResponseEntity<Page<Cliente>> trovaTutti(Pageable pageable) {
		log.info("*** Inizio ricerca clienti ***");
		Page<Cliente> trovaTutti = clienteService.getAll(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperid/{id}")
	@Operation(summary = "Visualizza tutti i clienti per id", description = "")
	public ResponseEntity<Cliente> trovaById(@PathVariable Long id) {
		log.info("*** Inizio Ricerca per id ***");
		Cliente trovaById = clienteService.findById(id);
		log.info("*** Fine ricerca per id ***");
		return new ResponseEntity<>(trovaById, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/inserisci")
	@Operation(summary = "Inserisce un cliente", description = "")
	public ResponseEntity<Cliente> inserisciCliente(@RequestBody Cliente cliente) {
		log.info("*** Inizio inserimento cliente ***");
		Cliente c = clienteService.insertCliente(cliente);
		log.info("*** Cliente salvato ***");
		return new ResponseEntity<>(c, HttpStatus.CREATED);

	}
	


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/aggiorna")
	@Operation(summary = "Aggiorna un cliente tramite il suo id", description = "")
	public ResponseEntity<Cliente> aggiornaCliente(@RequestBody Cliente cliente, @PathVariable Long id)
			throws ClienteException {
		log.info("*** Inizio aggornamento cliente ***");
		Cliente c = clienteService.update(cliente, id);
		log.info("*** Fine aggiornamento cliente ***");
		return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/cancella/{id}")
	@Operation(summary = "Elimina un cliente", description = "")
	public ResponseEntity<String> cancellaCliente(@PathVariable Long id) {
		log.info("*** Inizio cancellazione cliente ***");
		clienteService.deleteClienteById(id);
		log.info("*** Fine cancellazione cliente ***");
		return new ResponseEntity<>("Cliente cancellato correttamente!", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperragionesociale")
	@Operation(summary = "Visualizza clienti per nome", description = "")
	public ResponseEntity<Page<Cliente>> ordinaByNome(Pageable pageable) {
		log.info("*** Inizio ricerca clienti per nome ***");
		Page<Cliente> trovaTutti = clienteService.findAllByOrderByRagioneSocialeAsc(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** Cliente non trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperfatturato")
	@Operation(summary = "Visualizza clienti per fatturato annuale", description = "")
	public ResponseEntity<Page<Cliente>> ordinaByFatturatoAnnuale(Pageable pageable) {
		log.info("*** Inizio ricerca clienti per fatturato annuale ***");
		Page<Cliente> trovaTutti = clienteService.findAllByOrderByFatturatoAnnuale(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** Cliente non trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperdatainserimento")
	@Operation(summary = "Visualizza clienti per data inserimento", description = "")
	public ResponseEntity<Page<Cliente>> ordinaByDataInserimento(Pageable pageable) {
		log.info("*** Inizio ricerca clienti per data inserimento ***");
		Page<Cliente> trovaTutti = clienteService.findByOrderByDataInserimento(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** Cliente non trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperdataultimocontatto")
	@Operation(summary = "Visualizza clienti per data ultimo contatto", description = "")
	public ResponseEntity<Page<Cliente>> ordinaByDataUltimoContatto(Pageable pageable) {
		log.info("*** Inizio ricerca clienti per data ultimo contatto ***");
		Page<Cliente> trovaTutti = clienteService.findByOrderByDataUltimoContatto(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** Cliente non trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/ordinapersedelegale")
	@Operation(summary = "Visualizza clienti per sede legale", description = "")
	public ResponseEntity<Page<Cliente>> ordinaBySedeLegaleComuneProvincia(Pageable pageable) {
		log.info("*** Inizio ricerca clienti per sede legale ***");
		Page<Cliente> trovaTutti = clienteService.findByOrderBySedeLegaleComuneProvincia(pageable);
		if (trovaTutti.hasContent()) {
			log.info("*** Cliente trovato! ***");
			return new ResponseEntity<>(trovaTutti, HttpStatus.OK);

		} else {
			log.info("*** Cliente non trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperfatturatomaggioredi/{fatturatoAnnuale}")
	@Operation(summary = "Visualizza clienti per fatturato annuale maggiore uguale a", description = "")
	public ResponseEntity<Page<Cliente>> trovaPerFatturatoAnnualeMaggioreUguale(Pageable pageable,
			@PathVariable BigDecimal fatturatoAnnuale) {
		Page<Cliente> trovati = clienteService.findByFatturatoAnnualeGreaterThanEqual(pageable, fatturatoAnnuale);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperfatturatominoredi/{fatturatoAnnuale}")
	@Operation(summary = "Visualizza clienti per fatturato annuale minore uguale a", description = "")
	public ResponseEntity<Page<Cliente>> trovaPerFatturatoAnnualeMinoreUguale(Pageable pageable,
			@PathVariable BigDecimal fatturatoAnnuale) {
		Page<Cliente> trovati = clienteService.findByFatturatoAnnualeLessThanEqual(pageable, fatturatoAnnuale);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperfatturatocompresotra/{fatturatoMin}/{fatturatoMax}")
	@Operation(summary = "Visualizza clienti per fatturato annuale compreso", description = "")
	public ResponseEntity<Page<Cliente>> trovaByFatturatoAnnualeCompresoTra(Pageable pageable,
			@PathVariable BigDecimal fatturatoMin, @PathVariable BigDecimal fatturatoMax) {
		Page<Cliente> trovati = clienteService.findByFatturatoAnnualeBetween(pageable, fatturatoMin, fatturatoMax);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperinserimentomaggioredi/{dataInserimento}")
	@Operation(summary = "Visualizza clienti per data inserimento superiore ", description = "")
	public ResponseEntity<Page<Cliente>> trovaByDataInserimentoMaggioreUguale(Pageable pageable,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento) {
		Page<Cliente> trovati = clienteService.findByDataInserimentoGreaterThanEqual(pageable, dataInserimento);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperinserimentominoredi/{dataInserimento}")
	@Operation(summary = "Visualizza clienti per data inserimento inferiore", description = "")
	public ResponseEntity<Page<Cliente>> trovaByDataInserimentoMinoreUguale(Pageable pageable,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento) {
		Page<Cliente> trovati = clienteService.findByDataInserimentoLessThanEqual(pageable, dataInserimento);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperinserimentocompresotra/{dataMin}/{dataMax}")
	@Operation(summary = "Visualizza clienti per data inserimento compresa tra", description = "")
	public ResponseEntity<Page<Cliente>> trovaByDataInserimentoCompresaTra(Pageable pageable,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataMin,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataMax) {

		Page<Cliente> trovati = clienteService.findByDataInserimentoBetween(pageable, dataMin, dataMax);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperultimocontattomaggioredi/{dataUltimoContatto}")
	@Operation(summary = "Visualizza clienti per data ultimo contatto maggiore uguale a", description = "")
	public ResponseEntity<Page<Cliente>> trovaByDataUltimoContattoMaggioreUguale(Pageable pageable,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto) {
		Page<Cliente> trovati = clienteService.findByDataUltimoContattoGreaterThanEqual(pageable, dataUltimoContatto);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperultimocontattominoredi/{dataUltimoContatto}")
	@Operation(summary = "Visualizza clienti per data ultimo contatto minore uguale a", description = "")
	public ResponseEntity<Page<Cliente>> trovaByDataUltimoContattoMinoreUguale(Pageable pageable,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM.dd") LocalDate dataUltimoContatto) {
		Page<Cliente> trovati = clienteService.findByDataUltimoContattoLessThanEqual(pageable, dataUltimoContatto);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovaperinserimentocompresotra/{dataUltimoContattoMin}/{dataUltimoContattoMax}")
	@Operation(summary = "Visualizza clienti per data ultimo contatto compreso fra", description = "")
	public ResponseEntity<Page<Cliente>> trovaByDataUltimoContattoCompresaTra(Pageable pageable,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM.dd") LocalDate dataUltimoContattoMin,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM.dd") LocalDate dataUltimoContattoMax) {
		Page<Cliente> trovati = clienteService.findByDataUltimoContattoBetween(pageable, dataUltimoContattoMin,
				dataUltimoContattoMax);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/trovapernomelike/{ragioneSociale}")
	@Operation(summary = "CVisualizza clienti per ragione sociale ", description = "")
	public ResponseEntity<Page<Cliente>> trovaByRagioneSocialeContaining(Pageable pageable,
			@PathVariable String ragioneSociale) {
		Page<Cliente> trovati = clienteService.findByRagioneSocialeContaining(pageable, ragioneSociale);
		if (trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(trovati, HttpStatus.OK);
		}

	}

}