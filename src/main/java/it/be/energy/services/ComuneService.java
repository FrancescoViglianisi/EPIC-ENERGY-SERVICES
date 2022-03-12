package it.be.energy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.energy.repository.ComuneRepository;
@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	@Autowired
	ProvinciaService provinciaService;
	

	
}
