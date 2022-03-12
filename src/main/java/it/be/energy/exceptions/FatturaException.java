package it.be.energy.exceptions;

public class FatturaException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	
	public FatturaException(String message) {
		super(message);
	}
}
