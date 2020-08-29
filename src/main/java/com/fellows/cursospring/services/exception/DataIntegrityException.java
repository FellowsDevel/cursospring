package com.fellows.cursospring.services.exception;

public class DataIntegrityException extends Exception {

	private static final long serialVersionUID = 4047182923305486454L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
