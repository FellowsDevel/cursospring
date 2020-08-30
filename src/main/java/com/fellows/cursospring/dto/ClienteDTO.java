package com.fellows.cursospring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fellows.cursospring.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long	serialVersionUID	= -1881928248264711532L;

	private Integer				id;

	@NotEmpty( message = "O nome da categoria não pode ser vazia!" )
	@Size( min = 5, max = 80, message = "O nome deve ter entre 5 e 80 caracteres" )
	private String				nome;

	@NotEmpty( message = "O email deve ser informado" )
	@Email( message = "Email inválido" )
	private String				email;

	public ClienteDTO( Cliente obj ) {
		this.setId( obj.getId() );
		this.setNome( obj.getNome() );
		this.setEmail( obj.getEmail() );
	}

	public ClienteDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

}
