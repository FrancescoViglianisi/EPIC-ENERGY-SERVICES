package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.energy.repository.FatturaRepository;
@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;
}