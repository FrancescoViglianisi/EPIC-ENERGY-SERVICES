package it.be.energy.exceptions;

public class StatoFatturaException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public StatoFatturaException(String message) {
		super(message);
	}
}
