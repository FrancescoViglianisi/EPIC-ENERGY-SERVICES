package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;

import it.be.energy.repository.ClienteRepository;

public class ClienteService {

	
	@Autowired
	ClienteRepository clienteRepository;
}
