package com.fellows.cursospring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.repositories.CategoriaRepository;
import com.fellows.cursospring.services.exception.DataNotFoundException;

class CategoriaServiceTest {

	/*
	 * A anotaçao abaixo deve ser utiizada na classe a ser testada ela injeta as dependencias anotadas pelo @Mock quando
	 * se chama o initMocks
	 */
	@InjectMocks
	CategoriaService	service;

	@Mock
	CategoriaRepository	repo;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks( this );
	}

	@Test
	void testFind() throws DataNotFoundException {

		Categoria			cat			= new Categoria( 1, "Categoria" );

		Optional<Categoria>	optional	= Optional.ofNullable( cat );

		// regra do Mockito para injetar o objeto que viria do banco
		// pois estamos testando a classe de serviço e nao a do repositorio
		when( repo.findById( 1 ) ).thenReturn( optional );

		Categoria resp = service.find( 1 );

		assertEquals( cat.getNome(), resp.getNome() );
	}

	@Test
	void testFind_NotFound() {

		when( repo.findById( 1 ) ).thenReturn( Optional.empty() );

		assertThrows( DataNotFoundException.class, () -> {
			service.find( 1 );
		} );
	}

}
