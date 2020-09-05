package com.fellows.cursospring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.domain.Produto;

@Repository
@Transactional( readOnly = true )
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

//	@Query( "SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias" )
//	Page<Produto> search(
//			@Param( "nome" ) String nome,
//			@Param( "categorias" ) List<Categoria> categorias,
//			Pageable pageRequest );

	// pelo padrao do spring-boot JPA é que ao usar determinadas nomenclaturas é possível realizar a
	// consulta acima sem especificar o comando JPQL
	@Transactional( readOnly = true )
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
			String nome,
			List<Categoria> categorias,
			Pageable pageRequest );
}
