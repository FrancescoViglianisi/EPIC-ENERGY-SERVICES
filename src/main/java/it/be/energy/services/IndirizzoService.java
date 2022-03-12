package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;

import it.be.energy.repository.IndirizzoRepository;

public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
}
