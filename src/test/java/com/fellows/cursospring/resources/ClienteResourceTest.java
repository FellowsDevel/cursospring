package com.fellows.cursospring.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.dto.ClienteNewDTO;
import com.fellows.cursospring.repositories.ClienteRepository;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@AutoConfigureMockMvc
class ClienteResourceTest {

	private MockMvc					mockMvc;

	@Autowired
	private ObjectMapper			mapper;

	@Autowired
	private ClienteRepository		clienteRepo;

	@Autowired
	private WebApplicationContext	appContext;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup( appContext ).build();
	}

	@Test
	void insert() throws Exception {
		ClienteNewDTO cli = new ClienteNewDTO();
		cli.setBairro( "Bairro" );
		cli.setCep( "12.123-123" );
		cli.setCidadeId( 1 );
		cli.setComplemento( "complemento" );
		cli.setCpfOuCnpj( "123.123.123-12" );
		cli.setEmail( "nome@email.com" );
		cli.setLogradouro( "Logradouro do cliente novo" );
		cli.setNome( "Novo nome" );
		cli.setNumero( "123 B" );
		cli.setTelefone1( "2222111" );
		cli.setTelefone2( "333333" );

		mockMvc.perform( post( "/clientes" )
				.contentType( MediaType.APPLICATION_JSON )
				.content( mapper.writeValueAsString( cli ) ) ).andExpect( status().isCreated() );

		Cliente cliDb = clienteRepo.findById( 2 ).get();

		assertThat( cliDb.getEmail() ).isEqualTo( "nome@email.com" );
	}

	@Test
	void delete_cliente() throws Exception {
		
		insert();

		mockMvc.perform( delete( "/clientes/2" )
				.contentType( MediaType.APPLICATION_JSON )
				).andExpect( status().isOk() );

		Cliente cliDb = clienteRepo.findById( 2 ).orElse( null );

		assertNull( cliDb );
	}
}
