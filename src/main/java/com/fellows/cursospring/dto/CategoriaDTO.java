package com.fellows.cursospring.dto;

import java.io.Serializable;

import com.fellows.cursospring.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = -8365368161711483187L;
	private Integer id;
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		this.setId(obj.getId());
		this.setNome(obj.getNome());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
