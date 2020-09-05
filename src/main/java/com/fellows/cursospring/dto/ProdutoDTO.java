package com.fellows.cursospring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fellows.cursospring.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long	serialVersionUID	= -8388768987711483187L;
	private Integer				id;

	@NotEmpty( message = "O nome do produto não pode ser vazio!" )
	@Size( min = 5, max = 80, message = "O nome deve ter entre 5 e 80 caracteres" )
	private String				nome;

	private Double				preco;

	public ProdutoDTO() {
	}

	public ProdutoDTO( Produto obj ) {
		this.setId( obj.getId() );
		this.setNome( obj.getNome() );
		this.setPreco( obj.getPreco() );
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco( Double preco ) {
		this.preco = preco;
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

}
