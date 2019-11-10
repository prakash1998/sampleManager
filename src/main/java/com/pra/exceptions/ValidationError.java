package com.pra.exceptions;

public class ValidationError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationError() {
		super("just Validation Exception");
	}
	
	public ValidationError(String msg) {
		super(msg);
	}
}
