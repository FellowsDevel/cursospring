package com.fellows.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.domain.Produto;
import com.fellows.cursospring.repositories.ProdutoRepository;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaService categoriaService;
	
	public Produto find( Integer id ) throws DataNotFoundException {
		Optional<Produto> obj = repo.findById( id );
		return obj.orElseThrow( () -> new DataNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName() ) );
	}

	public Produto insert( Produto obj ) {
		obj.setId( null );
		return repo.save( obj );
	}

	public Produto update( Produto obj, Integer id ) throws DataNotFoundException {
		find( id );
		return repo.save( obj );
	}

	public void delete( Integer id ) throws DataIntegrityException {
		try {
			repo.deleteById( id );
		} catch ( DataIntegrityViolationException e ) {
			throw new DataIntegrityException( e.getMessage() );
		}
	}

	public Page<Produto> search(
			String nome,
			List<Integer> ids,
			Integer page,
			Integer size,
			String orderBy,
			String direction ) {
		PageRequest pr = PageRequest.of( page, size, Direction.valueOf( direction ), orderBy );
		List<Categoria> categorias = categoriaService.findAll( ids );
		return repo.findDistinctByNomeContainingAndCategoriasIn( nome, categorias, pr );

	}
}
