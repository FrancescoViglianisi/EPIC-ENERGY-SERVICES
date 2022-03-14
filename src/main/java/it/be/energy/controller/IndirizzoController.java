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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.model.Indirizzo;
import it.be.energy.services.IndirizzoService;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/indirizzo")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class IndirizzoController {
	
	@Autowired
	IndirizzoService indirizzoService;
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@Operation(summary = "Inserisci un indirizzo", description = "")
	public String insert(@RequestBody Indirizzo indirizzo) {
		log.info("*** Inizio inserimento cliente ***");
		indirizzoService.insert(indirizzo);
		return "Indirizzo inserito correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation(summary = "Cerca un indirizzo per l'Id", description = "")
	public ResponseEntity<Indirizzo> findByid(@PathVariable Long id) {
		log.info("*** Inizio Ricerca per id ***");
		return new ResponseEntity<>(indirizzoService.findById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation(summary = "Visualizza tutti gli indirizzi", description = "")
	public ResponseEntity<Page<Indirizzo>> getAll(Pageable pageable) {
		log.info("*** Inizio ricerca indirizzi ***");
		Page<Indirizzo> indirizzi = indirizzoService.findAll(pageable);
		if(indirizzi.hasContent()) {
			log.info("*** Indirizzo trovato! ***");
			return new ResponseEntity<>(indirizzi, HttpStatus.ACCEPTED);
		}else {
			log.info("*** Indirizzo trovato! ***");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping
	@Operation(summary = "Modifica un indirizzo", description = "")
	public String update(@RequestBody Indirizzo indirizzo , @PathVariable Long id) {
		log.info("*** Inizio aggornamento indirizzo ***");
		indirizzoService.update(indirizzo, id);
		return "indirizzo aggiornato correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@DeleteMapping("/delete")
	@Operation(summary = "Elimina un indirizzo", description = "")
	public String delete(@RequestParam Long id) {
		log.info("*** Inizio cancellazione indirizzo ***");
		indirizzoService.delete(id);
		return "indirizzo eliminato correttamente";
	}
	
}
