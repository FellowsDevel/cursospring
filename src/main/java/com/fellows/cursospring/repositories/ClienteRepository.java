package com.fellows.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fellows.cursospring.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// Colocar o atributo como readOnly melhora a performance devido a nao necessitar de locking no BD
	@Transactional( readOnly = true )
	Cliente findByEmail( String email );
}
