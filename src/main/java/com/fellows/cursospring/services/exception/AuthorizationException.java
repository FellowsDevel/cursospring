package com.fellows.cursospring.services.exception;

public class AuthorizationException extends Exception {

	private static final long serialVersionUID = 4046992923305486454L;

	
	public AuthorizationException( ) {
		super( "Acesso negado" );
	}
	
	public AuthorizationException( String msg ) {
		super( msg );
	}

	public AuthorizationException( String msg, Throwable cause ) {
		super( msg, cause );
	}
}
