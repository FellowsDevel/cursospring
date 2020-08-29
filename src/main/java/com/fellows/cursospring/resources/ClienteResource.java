package com.fellows.cursospring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.services.ClienteService;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws DataNotFoundException {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
