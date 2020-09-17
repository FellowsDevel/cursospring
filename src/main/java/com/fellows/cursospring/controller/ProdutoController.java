package com.fellows.cursospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fellows.cursospring.controller.utils.URL;
import com.fellows.cursospring.domain.Produto;
import com.fellows.cursospring.dto.ProdutoDTO;
import com.fellows.cursospring.services.ProdutoService;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping( value = "/produtos" )
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public ResponseEntity<Produto> find( @PathVariable Integer id ) throws DataNotFoundException {
		Produto obj = service.find( id );
		return ResponseEntity.ok().body( obj );
	}

	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<Page<ProdutoDTO>> paginate(
			@RequestParam( value = "nome" ) String nome,
			@RequestParam( value = "categorias" ) String categorias,
			@RequestParam( value = "page", defaultValue = "0" ) Integer page,
			@RequestParam( value = "size", defaultValue = "24" ) Integer size,
			@RequestParam( value = "orderBy", defaultValue = "nome" ) String orderBy,
			@RequestParam( value = "direction", defaultValue = "ASC" ) String direction ) {

		String				nomeDecoded	= URL.decodeParam( nome );
		List<Integer>		ids			= URL.decodeIntList( categorias );
		Page<Produto>		list		= service.search( nomeDecoded, ids, page, size, orderBy, direction );
		Page<ProdutoDTO>	listDTO		= list.map( obj -> new ProdutoDTO( obj ) );
		return ResponseEntity.ok().body( listDTO );
	}

}
