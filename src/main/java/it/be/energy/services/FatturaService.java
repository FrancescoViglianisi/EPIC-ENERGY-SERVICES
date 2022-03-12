package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;

import it.be.energy.repository.FatturaRepository;

public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;
}
