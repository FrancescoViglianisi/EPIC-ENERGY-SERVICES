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
import it.be.energy.model.Provincia;
import it.be.energy.services.ProvinciaService;

public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation
	public ResponseEntity<Provincia> findByid(@PathVariable Long id) {
		return new ResponseEntity<>(provinciaService.findById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation
	public ResponseEntity<Page<Provincia>> getAll(Pageable pageable) {
		Page<Provincia> province = provinciaService.findAll(pageable);
		if(province.hasContent()) {
			return new ResponseEntity<>(province, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
