package com.rbc.assignment.service.exception;

public class StockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockException(String message) {
		super(message);
	}

	public StockException(String message, Throwable e) {
		super(message, e);
		
	}
}
