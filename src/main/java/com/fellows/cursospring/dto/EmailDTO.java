package com.fellows.cursospring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {

	private static final long	serialVersionUID	= -4153871820612785010L;

	@NotEmpty( message = "O email deve ser informado" )
	@Email( message = "Email inválido" )
	private String				email;

	public EmailDTO() {
	}

	public EmailDTO( String email ) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

}
