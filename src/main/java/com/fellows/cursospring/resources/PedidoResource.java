package com.fellows.cursospring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fellows.cursospring.domain.Pedido;
import com.fellows.cursospring.services.PedidoService;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws DataNotFoundException {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
