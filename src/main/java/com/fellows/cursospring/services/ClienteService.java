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
import com.fellows.cursospring.domain.enums.Perfil;
import com.fellows.cursospring.domain.enums.TipoCliente;
import com.fellows.cursospring.dto.ClienteDTO;
import com.fellows.cursospring.dto.ClienteNewDTO;
import com.fellows.cursospring.repositories.ClienteRepository;
import com.fellows.cursospring.repositories.EnderecoRepository;
import com.fellows.cursospring.security.UserSS;
import com.fellows.cursospring.services.exception.AuthorizationException;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository				clienteRepository;

	@Autowired
	private EnderecoRepository				enderecoRepository;

	@Autowired
	private static BCryptPasswordEncoder	be;

	public Cliente find( Integer id ) throws DataNotFoundException, AuthorizationException {

		UserSS user = UserService.authenticated();
		if ( user == null || !user.hasRole( Perfil.ADMIN ) && !id.equals( user.getId() ) ) {
			throw new AuthorizationException( "Acesso negado!" );
		}

		Optional<Cliente> obj = clienteRepository.findById( id );
		return obj.orElseThrow( () -> new DataNotFoundException(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName() ) );
	}

	public Cliente findByEmail( String email ) {
		return clienteRepository.findByEmail( email );
	}

	@Transactional
	public Cliente insert( Cliente obj ) {
		obj.setId( null );
		obj = clienteRepository.save( obj );
		enderecoRepository.saveAll( obj.getEnderecos() );
		return obj;
	}

	public Cliente update( Cliente obj, Integer id ) throws DataNotFoundException, AuthorizationException {
		Cliente c = clienteRepository.findById( id ).get();
		updateData( c, obj );
		return clienteRepository.save( c );
	}

	private void updateData( Cliente c, Cliente obj ) {
		c.setNome( obj.getNome() );
		c.setEmail( obj.getEmail() );
		c.setSenha( obj.getSenha() );
	}

	public void delete( Integer id ) throws DataIntegrityException {
		try {
			clienteRepository.deleteById( id );
		} catch ( DataIntegrityViolationException e ) {
			throw new DataIntegrityException( "Não é possível excluir um cliente com pedidos relacionados" );
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> paginate( Integer page, Integer size, String orderBy, String direction ) {
		PageRequest pr = PageRequest.of( page, size, Direction.valueOf( direction ), orderBy );
		return clienteRepository.findAll( pr );
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
