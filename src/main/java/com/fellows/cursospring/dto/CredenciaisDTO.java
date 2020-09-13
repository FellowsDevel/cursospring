package com.fellows.cursospring.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

	private static final long	serialVersionUID	= -1919885759176938707L;

	private String				email;
	private String				senha;

	public CredenciaisDTO() {
		// TODO Auto-generated constructor stub
	}

	public CredenciaisDTO( String email, String senha ) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}

}
