package it.be.energy.util;

import java.math.BigDecimal;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import it.be.energy.model.Cliente;
import it.be.energy.model.Fattura;
import it.be.energy.model.Indirizzo;
import it.be.energy.model.StatoFattura;
import it.be.energy.model.TipoCliente;
import it.be.energy.repository.ClienteRepository;
import it.be.energy.repository.ComuneRepository;
import it.be.energy.repository.FatturaRepository;
import it.be.energy.repository.IndirizzoRepository;
import it.be.energy.repository.StatoFatturaRepository;

@Component
public class RunnerMain implements CommandLineRunner {

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	IndirizzoRepository indirizzoRepository;

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	StatoFatturaRepository statoFatturaRepository;
	
	@Autowired
	FatturaRepository fatturaRepository;

	@Override
	public void run(String... args) throws Exception {
		initIndirizzi();
		initClienti();
		initFatture();
	}

	private void initIndirizzi() throws Exception {
		Indirizzo i1 = new Indirizzo();
		i1.setCap(28060);
		i1.setCivico("10");
		i1.setLocalita("Marittima");
		i1.setVia("Roma");
		i1.setComune(comuneRepository.findById(1l).get());
		indirizzoRepository.save(i1);

		Indirizzo i2 = new Indirizzo();
		i2.setCap(29050);
		i2.setCivico("75a");
		i2.setLocalita("Montana");
		i2.setVia("Chiusa");
		i2.setComune(comuneRepository.findById(2l).get());
		indirizzoRepository.save(i2);

		Indirizzo i3 = new Indirizzo();
		i3.setCap(25010);
		i3.setCivico("25");
		i3.setLocalita("Città");
		i3.setVia("Bologna");
		i3.setComune(comuneRepository.findById(3l).get());
		indirizzoRepository.save(i3);

	}

	private void initClienti() throws Exception {
		Cliente c1 = new Cliente();
		c1.setCognomeContatto("Viglianisi");
		c1.setNomeContatto("Francesco");
		c1.setEMailContatto("viglianisi@gmail.com");
		c1.setFatturatoAnnuale(BigDecimal.valueOf(10.000));
		c1.setDataInserimento(LocalDate.parse("2005-01-01"));
		c1.setDataUltimoContatto(LocalDate.parse("2007-05-15"));
		c1.setEMail("Epicode@gmail.com");
		c1.setSedeLegale(indirizzoRepository.getById(1l));
		c1.setSedeOperativa(indirizzoRepository.getById(1l));
		c1.setPartitaIva("123456789");
		c1.setPec("Francesco Vigliansi");
		c1.setRagioneSociale("Epicode");
		c1.setTelefono("333-555-4321");
		c1.setTelefonoContatto("393-525-4751");
		c1.setTipoCliente(TipoCliente.SPA);
		clienteRepository.save(c1);
		


		Cliente c2 = new Cliente();
		c2.setCognomeContatto("Rossi");
		c2.setNomeContatto("Mario");
		c2.setEMailContatto("rossi@libero.it");
		c2.setFatturatoAnnuale(BigDecimal.valueOf(250.000));
		c2.setDataInserimento(LocalDate.parse("2002-01-01"));
		c2.setDataUltimoContatto(LocalDate.parse("2010-08-15"));
		c2.setEMail("lg@libero.it");
		c2.setSedeLegale(indirizzoRepository.getById(2l));
		c2.setSedeOperativa(indirizzoRepository.getById(2l));
		c2.setPartitaIva("987654321");
		c2.setPec("Mario Rossi");
		c2.setRagioneSociale("LG");
		c2.setTelefono("393-534-4156");
		c2.setTelefonoContatto("321-963-2964");
		c2.setTipoCliente(TipoCliente.SRL);
		clienteRepository.save(c2);

		Cliente c3 = new Cliente();
		c3.setCognomeContatto("Fazio");
		c3.setNomeContatto("Gianni");
		c3.setEMailContatto("fazio.gianni@hotmail.com");
		c3.setFatturatoAnnuale(BigDecimal.valueOf(950.000));
		c3.setDataInserimento(LocalDate.parse("2000-12-01"));
		c3.setDataUltimoContatto(LocalDate.parse("2018-11-20"));
		c3.setEMail("spotify@hotmail.com");
		c3.setSedeLegale(indirizzoRepository.getById(3l));
		c3.setSedeOperativa(indirizzoRepository.getById(3l));
		c3.setPartitaIva("543216789");
		c3.setPec("Gianni Fazio");
		c3.setRagioneSociale("Spotify");
		c3.setTelefono("334-087-0361");
		c3.setTelefonoContatto("339-876-0192");
		c3.setTipoCliente(TipoCliente.SAS);
		clienteRepository.save(c3);

	}
	
	
	private void initFatture() throws Exception {
		Fattura f1 = new Fattura();
		StatoFattura sf = new StatoFattura();
		sf.setNome("pagato1");
		f1.setAnno(2021);
		f1.setData(LocalDate.parse("2021-03-05"));
		f1.setImporto(new BigDecimal("2500"));
		f1.setNumeroFattura(1);
		f1.setCliente(clienteRepository.findById(1l).get());
		f1.setStatoFattura(sf);
		statoFatturaRepository.save(sf);
		fatturaRepository.save(f1);
	
	
		Fattura f2 = new Fattura();
		StatoFattura sf2 = new StatoFattura();
		sf2.setNome("non pagato");
		f2.setAnno(2020);
		f2.setData(LocalDate.parse("2020-05-03"));
		f2.setImporto(new BigDecimal("3000"));
		f2.setNumeroFattura(2);
		f2.setCliente(clienteRepository.findById(2l).get());
		f2.setStatoFattura(sf2);
		statoFatturaRepository.save(sf2);
		fatturaRepository.save(f2);
		
		Fattura f3 = new Fattura();
		StatoFattura sf3 = new StatoFattura();
		sf3.setNome("pagato");
		f3.setAnno(2019);
		f3.setData(LocalDate.parse("2019-11-10"));
		f3.setImporto(new BigDecimal("7500"));
		f3.setNumeroFattura(3);
		f3.setCliente(clienteRepository.findById(3l).get());
		f3.setStatoFattura(sf3);
		statoFatturaRepository.save(sf3);
		fatturaRepository.save(f3);
	}
}