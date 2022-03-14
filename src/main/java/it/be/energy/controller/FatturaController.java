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
import it.be.energy.model.Cliente;
import it.be.energy.model.Fattura;
import it.be.energy.services.FatturaService;

@RestController
@RequestMapping("/fattura")

public class FatturaController {
	
	@Autowired
	FatturaService fatturaService;
	
	@PreAuthorize
	@PostMapping
	@Operation
	public String insert(@RequestBody Fattura fattura) {
		fatturaService.insert(fattura);
		return "Cliente inserito correttamente";
	}
	
	@PreAuthorize
	@GetMapping
	@Operation
	public ResponseEntity<Fattura> findByid(@PathVariable Long id) {
		return new ResponseEntity<>(fatturaService.findById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize
	@GetMapping
	@Operation
	public ResponseEntity<Page<Fattura>> getAll(Pageable pageable) {
		Page<Fattura> clienti = fatturaService.findAll(pageable);
		if(clienti.hasContent()) {
			return new ResponseEntity<>(clienti, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize
	@PutMapping
	@Operation
	public String update(@RequestBody Fattura fattura , @PathVariable Long id) {
		fatturaService.update(fattura, id);
		return "Fattura aggiornata correttamente";
	}
	
	@PreAuthorize
	@GetMapping
	@Operation
	public String delete(@RequestParam Long id) {
		fatturaService.delete(id);
		return "Fattura eliminata correttamente";
	}
	

}
