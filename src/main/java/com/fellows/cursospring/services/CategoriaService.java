package com.fellows.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.dto.CategoriaDTO;
import com.fellows.cursospring.repositories.CategoriaRepository;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find( Integer id ) throws DataNotFoundException {
		Optional<Categoria> obj = repo.findById( id );
		return obj.orElseThrow( () -> new DataNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName() ) );
	}

	@Transactional
	public Categoria insert( Categoria obj ) {
		obj.setId( null );
		obj = repo.save( obj );
		return obj;
	}

	public Categoria update( Categoria obj, Integer id ) throws DataNotFoundException {
		Categoria c = find( id );
		updateData( c, obj );
		return repo.save( c );
	}

	private void updateData( Categoria c, Categoria obj ) {
		c.setNome( obj.getNome() );
	}

	public void delete( Integer id ) throws DataIntegrityException {
		try {
			repo.deleteById( id );
		} catch ( DataIntegrityViolationException e ) {
			throw new DataIntegrityException( "Não é possível excluir uma categoria com produtos associados" );
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public List<Categoria> findAll( List<Integer> ids ) {
		return repo.findAllById( ids );
	}

	public Page<Categoria> paginate( Integer page, Integer size, String orderBy, String direction ) {
		PageRequest pr = PageRequest.of( page, size, Direction.valueOf( direction ), orderBy );
		return repo.findAll( pr );
	}

	public static Categoria fromDTO( CategoriaDTO objDTO ) {
		return new Categoria( objDTO.getId(), objDTO.getNome() );
	}

}
