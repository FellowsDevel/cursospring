package com.fellows.cursospring.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.services.ClienteService;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping( value = "/clientes" )
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<Cliente> find( @PathVariable Integer id ) throws DataNotFoundException {
		Cliente obj = service.find( id );
		return ResponseEntity.ok().body( obj );
	}

	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<Void> insert( @RequestBody Cliente obj ) {
		obj = service.insert( obj );
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" ).buildAndExpand( obj.getId() )
				.toUri();
		return ResponseEntity.created( uri ).build();
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Void> update( @RequestBody Cliente obj, @PathVariable Integer id )
			throws DataNotFoundException {
		obj = service.update( obj, id );
		return ResponseEntity.noContent().build();
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public void delete( @PathVariable Integer id ) throws DataIntegrityException {
		service.delete( id );
	}
}
