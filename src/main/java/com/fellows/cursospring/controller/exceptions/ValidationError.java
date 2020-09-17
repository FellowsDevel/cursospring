package com.fellows.cursospring.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long	serialVersionUID	= -8500083259764266233L;

	private List<FieldMessage>	errors				= new ArrayList<FieldMessage>();

	public ValidationError( Integer status, String msg, Long timestamp ) {
		super( status, msg, timestamp );
	}

	public List<FieldMessage> getErrors() {
		return this.errors;
	}

	public void addMessge( String fieldName, String message ) {
		this.errors.add( new FieldMessage( fieldName, message ) );
	}

}
