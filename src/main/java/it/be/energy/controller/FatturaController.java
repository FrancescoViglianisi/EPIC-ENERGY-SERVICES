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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.model.Fattura;
import it.be.energy.services.FatturaService;

@RestController
@RequestMapping("/fattura")
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
	@GetMapping("/find_id/{id}")
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
	@GetMapping("/find/{ragioneSociale}")
	@Operation(summary = "Cerca una fattura per ragione sociale del cliente", description = "")
	public ResponseEntity<Page<Fattura>> findByClienteRagioneSocialeContaining(Pageable pageable, @PathVariable String ragioneSociale) {
		Page<Fattura> trovate = fatturaService.findByClienteRagioneSocialeContaining(pageable, ragioneSociale); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/stato/{id}")
	@Operation(summary = "Cerca una fattura per lo stato", description = "")
	public ResponseEntity<Page<Fattura>> findByStatoId(Pageable pageable, @PathVariable Long id) {
		Page<Fattura> trovate = fatturaService.findByStato(pageable, id); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/data/{data}")
	@Operation(summary = "Cerca una fattura per la data", description = "")
	public ResponseEntity<Page<Fattura>> findByData(Pageable pageable, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
		Page<Fattura> trovate = fatturaService.findByData(pageable, data); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/anno/{anno}")
	@Operation(summary = "Cerca una fattura per l'anno", description = "")
	public ResponseEntity<Page<Fattura>> findByAnno(Pageable pageable,@PathVariable Integer anno) {
		Page<Fattura> trovate = fatturaService.findByAnno(pageable, anno); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}

	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/importo/{minimo}/{massimo}")
	@Operation(summary = "Cerca una fattura per l'importo", description = "")
	public ResponseEntity<Page<Fattura>> findByImportoBetween(Pageable pageable, @PathVariable BigDecimal minimo,@PathVariable BigDecimal massimo) {
		Page<Fattura> trovate = fatturaService.findByImportoBetween(pageable, minimo , massimo); 
		if(trovate.hasContent()) {
			return new ResponseEntity<>(trovate , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(trovate , HttpStatus.NOT_FOUND);
		}
	}
	
	
}
