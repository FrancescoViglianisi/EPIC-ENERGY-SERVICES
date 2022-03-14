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
import it.be.energy.model.Provincia;
import it.be.energy.services.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/provincia")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation(summary = "Cerca una provincia per l'id", description = "")
	public ResponseEntity<Provincia> findByid(@PathVariable Long id) {

		return new ResponseEntity<>(provinciaService.findById(id), HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation(summary = "Visualizza tutte le province", description = "")
	public ResponseEntity<Page<Provincia>> getAll(Pageable pageable) {
		Page<Provincia> province = provinciaService.findAll(pageable);
		if (province.hasContent()) {
			return new ResponseEntity<>(province, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
