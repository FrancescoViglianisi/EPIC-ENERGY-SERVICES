package it.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import it.be.energy.model.Comune;
import it.be.energy.services.ComuneService;

public class ComuneController {

	@Autowired
	ComuneService comuneService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation
	public ResponseEntity<Comune> findByid(@PathVariable Long id) {
		return new ResponseEntity<>(comuneService.findById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation
	public ResponseEntity<Page<Comune>> getAll(Pageable pageable) {
		Page<Comune> comuni = comuneService.findAll(pageable);
		if(comuni.hasContent()) {
			return new ResponseEntity<>(comuni, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
