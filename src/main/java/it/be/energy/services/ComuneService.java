package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;

import it.be.energy.repository.ComuneRepository;

public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
}
