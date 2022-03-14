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
import it.be.energy.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@PreAuthorize
	@PostMapping
	@Operation
	public String insertCliente(@RequestBody Cliente cliente) {
		clienteService.insertCliente(cliente);
		return "Cliente inserito correttamente";
	}
	
	@PreAuthorize
	@GetMapping
	@Operation
	public ResponseEntity<Cliente> getByid(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.getById(id) , HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize
	@GetMapping
	@Operation
	public ResponseEntity<Page<Cliente>> getAll(Pageable pageable) {
		Page<Cliente> clienti = clienteService.getAll(pageable);
		if(clienti.hasContent()) {
			return new ResponseEntity<>(clienti, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize
	@PutMapping
	@Operation
	public String update(@RequestBody Cliente cliente , @PathVariable Long id) {
		clienteService.update(cliente);
		return "Cliente aggiornato correttamente";
	}
	
	@PreAuthorize
	@GetMapping
	@Operation
	public String delete(@RequestParam Long id) {
		clienteService.deleteClienteById(id);
		return "Cliente eliminato correttamente";
	}
	
	
	

}
