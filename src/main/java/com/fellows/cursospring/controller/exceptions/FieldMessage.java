package com.fellows.cursospring.controller.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long	serialVersionUID	= 2459973182372943099L;

	private String				filedName;
	private String				message;

	public FieldMessage() {
	}

	public FieldMessage( String filedName, String message ) {
		super();
		this.filedName = filedName;
		this.message = message;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName( String filedName ) {
		this.filedName = filedName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage( String message ) {
		this.message = message;
	}

}
