package com.fellows.cursospring.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fellows.cursospring.domain.Pedido;
import com.fellows.cursospring.services.PedidoService;
import com.fellows.cursospring.services.exception.AuthorizationException;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping( value = "/pedidos" )
public class PedidoController {

	@Autowired
	private PedidoService service;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<Pedido> find( @PathVariable Integer id ) throws DataNotFoundException {
		Pedido obj = service.find( id );
		return ResponseEntity.ok().body( obj );
	}

	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<Void> insert( @RequestBody Pedido obj ) throws DataNotFoundException, AuthorizationException {
		obj = service.insert( obj );
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" ).buildAndExpand( obj.getId() )
			.toUri();
		return ResponseEntity.created( uri ).build();
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Void> update( @RequestBody Pedido obj, @PathVariable Integer id )
		throws DataNotFoundException {
		obj = service.update( obj, id );
		return ResponseEntity.noContent().build();
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete( @PathVariable Integer id ) throws DataIntegrityException {
		service.delete( id );
		return ResponseEntity.noContent().build();
	}

	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<Page<Pedido>> paginate(
		@RequestParam( value = "page", defaultValue = "0" ) Integer page,
		@RequestParam( value = "size", defaultValue = "24" ) Integer size,
		@RequestParam( value = "orderBy", defaultValue = "instante" ) String orderBy,
		@RequestParam( value = "direction", defaultValue = "DESC" ) String direction ) throws AuthorizationException, DataNotFoundException {

		Page<Pedido> list = service.findPage( page, size, orderBy, direction );
		return ResponseEntity.ok().body( list );
	}
}
