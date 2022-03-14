package it.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.model.Comune;
import it.be.energy.services.ComuneService;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/comune")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ComuneController {

	@Autowired
	ComuneService comuneService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation(summary = "Cerca un comune per l'id", description = "")
	public ResponseEntity<Comune> findByid(@PathVariable Long id) {
		log.info("*** Inizio ricerca comuni ***");
		return new ResponseEntity<>(comuneService.findById(id) , HttpStatus.ACCEPTED);
	}
		
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation(summary = "Visualizza tutti i comuni", description = "")
	public ResponseEntity<Page<Comune>> getAll(Pageable pageable) {
		Page<Comune> comuni = comuneService.findAll(pageable);
		if(comuni.hasContent()) {
			return new ResponseEntity<>(comuni, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
