package com.fellows.cursospring.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.dto.CategoriaDTO;
import com.fellows.cursospring.services.CategoriaService;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping( value = "/categorias" )
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<Categoria> find( @PathVariable Integer id ) throws DataNotFoundException {
		Categoria obj = service.find( id );
		return ResponseEntity.ok().body( obj );
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<Void> insert( @Valid @RequestBody CategoriaDTO objDTO ) {
		Categoria	obj	= service.insert( CategoriaService.fromDTO( objDTO ) );
		URI			uri	= ServletUriComponentsBuilder.fromCurrentRequest().path( "/{id}" ).buildAndExpand( obj.getId() )
				.toUri();
		return ResponseEntity.created( uri ).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Void> update( @Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id )
			throws DataNotFoundException {
		objDTO.setId( id );
		service.update( CategoriaService.fromDTO( objDTO ), id );
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public void delete( @PathVariable Integer id ) throws DataIntegrityException {
		service.delete( id );
	}

	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria>		list	= service.findAll();
		List<CategoriaDTO>	listDTO	= list.stream().map( obj -> new CategoriaDTO( obj ) )
				.collect( Collectors.toList() );
		return ResponseEntity.ok().body( listDTO );
	}

	@RequestMapping( value = "/page", method = RequestMethod.GET )
	public ResponseEntity<Page<CategoriaDTO>> paginate(
			@RequestParam( value = "page", defaultValue = "0" ) Integer page,
			@RequestParam( value = "size", defaultValue = "24" ) Integer size,
			@RequestParam( value = "orderBy", defaultValue = "nome" ) String orderBy,
			@RequestParam( value = "direction", defaultValue = "ASC" ) String direction ) {

		Page<Categoria>		list	= service.paginate( page, size, orderBy, direction );
		Page<CategoriaDTO>	listDTO	= list.map( obj -> new CategoriaDTO( obj ) );
		return ResponseEntity.ok().body( listDTO );
	}

}
