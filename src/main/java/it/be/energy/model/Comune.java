package it.be.energy.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comune {
	
	@Id
	private Long codiceComune;
	private String nome;
	private Provincia provincia;
	
}
