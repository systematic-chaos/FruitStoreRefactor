package com.cybercom.fruitstore.svc;

public class FruitException extends Exception {
	private static final long serialVersionUID = 8453764078563798520L;
	
	public FruitException() {
	}
	
	public FruitException(Throwable cause) {
		super(cause);
	}
	
	public FruitException(String message) {
		super(message);
	}
	
	public FruitException(Throwable cause, String message) {
		super(message, cause);
	}
}
