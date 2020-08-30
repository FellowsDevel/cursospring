package com.fellows.cursospring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.dto.ClienteDTO;
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
	public ResponseEntity<Void> update( @Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id )
			throws DataNotFoundException {
		objDTO.setId( id );
		service.update( service.fromDTO( objDTO ), id );
		return ResponseEntity.noContent().build();
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public void delete( @PathVariable Integer id ) throws DataIntegrityException {
		service.delete( id );
	}

	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente>		list	= service.findAll();
		List<ClienteDTO>	listDTO	= list.stream().map( obj -> new ClienteDTO( obj ) )
				.collect( Collectors.toList() );
		return ResponseEntity.ok().body( listDTO );
	}

	@RequestMapping( value = "/page", method = RequestMethod.GET )
	public ResponseEntity<Page<ClienteDTO>> paginate(
			@RequestParam( value = "page", defaultValue = "0" ) Integer page,
			@RequestParam( value = "size", defaultValue = "24" ) Integer size,
			@RequestParam( value = "orderBy", defaultValue = "nome" ) String orderBy,
			@RequestParam( value = "direction", defaultValue = "ASC" ) String direction ) {

		Page<Cliente>		list	= service.paginate( page, size, orderBy, direction );
		Page<ClienteDTO>	listDTO	= list.map( obj -> new ClienteDTO( obj ) );
		return ResponseEntity.ok().body( listDTO );
	}
}
