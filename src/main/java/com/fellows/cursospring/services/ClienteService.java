package com.fellows.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fellows.cursospring.domain.Cidade;
import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.domain.Endereco;
import com.fellows.cursospring.domain.enums.TipoCliente;
import com.fellows.cursospring.dto.ClienteDTO;
import com.fellows.cursospring.dto.ClienteNewDTO;
import com.fellows.cursospring.repositories.ClienteRepository;
import com.fellows.cursospring.repositories.EnderecoRepository;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository				repo;

	@Autowired
	private EnderecoRepository				enderecoRepository;

	@Autowired
	private static BCryptPasswordEncoder	be;

	public Cliente find( Integer id ) throws DataNotFoundException {
		Optional<Cliente> obj = repo.findById( id );
		return obj.orElseThrow( () -> new DataNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName() ) );
	}

	public Cliente findByEmail( String email ) {
		return repo.findByEmail( email );
	}

	@Transactional
	public Cliente insert( Cliente obj ) {
		obj.setId( null );
		obj = repo.save( obj );
		enderecoRepository.saveAll( obj.getEnderecos() );
		return obj;
	}

	public Cliente update( Cliente obj, Integer id ) throws DataNotFoundException {
		Cliente c = find( id );
		updateData( c, obj );
		return repo.save( c );
	}

	private void updateData( Cliente c, Cliente obj ) {
		c.setNome( obj.getNome() );
		c.setEmail( obj.getEmail() );
	}

	public void delete( Integer id ) throws DataIntegrityException {
		try {
			repo.deleteById( id );
		} catch ( DataIntegrityViolationException e ) {
			throw new DataIntegrityException( "Não é possível excluir um cliente com pedidos relacionados" );
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> paginate( Integer page, Integer size, String orderBy, String direction ) {
		PageRequest pr = PageRequest.of( page, size, Direction.valueOf( direction ), orderBy );
		return repo.findAll( pr );
	}

	public Cliente fromDTO( ClienteDTO objDTO ) {
		return new Cliente( objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null );
	}

	public static Cliente fromDTO( ClienteNewDTO objDTO ) {
		Cliente		cli	= new Cliente( null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum( objDTO.getTipo() ), be.encode( objDTO.getSenha() ) );
		Cidade		cid	= new Cidade( objDTO.getCidadeId(), null, null );
		Endereco	end	= new Endereco( null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid );

		cli.getEnderecos().add( end );
		cli.getTelefones().add( objDTO.getTelefone1() );
		if ( objDTO.getTelefone2() != null ) {
			cli.getTelefones().add( objDTO.getTelefone2() );
		}
		if ( objDTO.getTelefone3() != null ) {
			cli.getTelefones().add( objDTO.getTelefone3() );
		}
		return cli;
	}
}
