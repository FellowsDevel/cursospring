package com.fellows.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fellows.cursospring.domain.Pedido;
import com.fellows.cursospring.repositories.PedidoRepository;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find( Integer id ) throws DataNotFoundException {
		Optional<Pedido> obj = repo.findById( id );
		return obj.orElseThrow( () -> new DataNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName() ) );
	}

	public Pedido insert( Pedido obj ) {
		obj.setId( null );
		return repo.save( obj );
	}

	public Pedido update( Pedido obj, Integer id ) throws DataNotFoundException {
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
}
