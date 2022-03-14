package it.be.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import it.be.energy.model.Indirizzo;
import it.be.energy.services.IndirizzoService;
@RestController
@RequestMapping("/indirizzo  ")
public class IndirizzoController {
	
	@Autowired
	IndirizzoService indirizzoService;
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@Operation
	public String insert(@RequestBody Indirizzo indirizzo) {
		indirizzoService.insert(indirizzo);
		return "Indirizzo inserito correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation
	public ResponseEntity<Indirizzo> findByid(@PathVariable Long id) {
		return new ResponseEntity<>(indirizzoService.findById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation
	public ResponseEntity<Page<Indirizzo>> getAll(Pageable pageable) {
		Page<Indirizzo> indirizzi = indirizzoService.findAll(pageable);
		if(indirizzi.hasContent()) {
			return new ResponseEntity<>(indirizzi, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping
	@Operation
	public String update(@RequestBody Indirizzo indirizzo , @PathVariable Long id) {
		indirizzoService.update(indirizzo, id);
		return "indirizzo aggiornato correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/delete")
	@Operation
	public String delete(@RequestParam Long id) {
		indirizzoService.delete(id);
		return "indirizzo eliminato correttamente";
	}
	
}
