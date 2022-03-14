package it.be.energy.controller;

import java.math.BigDecimal;
import java.util.Date;

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
import it.be.energy.model.Fattura;
import it.be.energy.model.StatoFattura;
import it.be.energy.services.FatturaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fattura")
@Slf4j
@SecurityRequirement(name = "bearerAuth")


public class FatturaController {
	
	@Autowired
	FatturaService fatturaService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@Operation(summary = "Inserisci le fatture", description = "")
	public String insert(@RequestBody Fattura fattura) {
		fatturaService.insert(fattura);
		return "Fattura inserita correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find/{id}")
	@Operation(summary = "Cerca le fatture per id", description = "")
	public ResponseEntity<Fattura> findByid(@PathVariable Long id) {
		return new ResponseEntity<>(fatturaService.findById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/getAll")
	@Operation(summary = "Visualizza tutte le fatture", description = "")
	public ResponseEntity<Page<Fattura>> getAll(Pageable pageable) {
		Page<Fattura> clienti = fatturaService.findAll(pageable);
		if(clienti.hasContent()) {
			return new ResponseEntity<>(clienti, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping
	@Operation(summary = "Modifica una fattura", description = "")
	public String update(@RequestBody Fattura fattura , @PathVariable Long id) {
		fatturaService.update(fattura, id);
		return "Fattura aggiornata correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@DeleteMapping("/delete")
	@Operation(summary = "Elimina una fattura", description = "")
	public String delete(@RequestParam Long id) {
		fatturaService.delete(id);
		return "Fattura eliminata correttamente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/find")
	@Operation(summary = "Cerca una fattura per ragione sociale del cliente", description = "")
	public ResponseEntity<Page<Fattura>> findByClienteRagioneSocialeContaining(Pageable pageable, String nome) {
		Page<Fattura> trovate = fatturaService.findByClienteRagioneSocialeContaining(pageable, nome); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/stato")
	@Operation(summary = "Cerca una fattura per lo stato", description = "")
	public ResponseEntity<Page<Fattura>> findByStato(Pageable pageable, StatoFattura stato) {
		Page<Fattura> trovate = fatturaService.findByStato(pageable, stato); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/data")
	@Operation(summary = "Cerca una fattura per la data", description = "")
	public ResponseEntity<Page<Fattura>> findByData(Pageable pageable, Date data) {
		Page<Fattura> trovate = fatturaService.findByData(pageable, data); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/anno")
	@Operation(summary = "Cerca una fattura per l'anno", description = "")
	public ResponseEntity<Page<Fattura>> findByAnno(Pageable pageable, Integer anno) {
		Page<Fattura> trovate = fatturaService.findByAnno(pageable, anno); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}

	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/importo")
	@Operation(summary = "Cerca una fattura per l'importo", description = "")
	public ResponseEntity<Page<Fattura>> findByImportoBetween(Pageable pageable, BigDecimal minimo, BigDecimal massimo) {
		Page<Fattura> trovate = fatturaService.findByImportoBetween(pageable, minimo , massimo); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}
	}

}
