package com.pra.exceptions;

public class DuplicateEntry extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateEntry() {
		super("Duplicate record");
	}
	
	public DuplicateEntry(String msg) {
		super(msg);
	}
}
