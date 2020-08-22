package com.fellows.cursospring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.services.CategoriaService;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws DataNotFoundException {
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
