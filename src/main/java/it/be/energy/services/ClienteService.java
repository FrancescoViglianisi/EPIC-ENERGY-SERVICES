package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.energy.model.Cliente;
import it.be.energy.repository.ClienteRepository;
@Service
public class ClienteService {

	
	@Autowired
	ClienteRepository clienteRepository;
	
		
}
