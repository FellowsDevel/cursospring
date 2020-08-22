package com.fellows.cursospring.services.exception;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 4047182923305486454L;

	public DataNotFoundException(String msg) {
		super(msg);
	}

	public DataNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
