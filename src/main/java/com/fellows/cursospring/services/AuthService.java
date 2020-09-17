package com.fellows.cursospring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.services.exception.AuthorizationException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteService			clienteService;

	@Autowired
	private BCryptPasswordEncoder	be;

	@Autowired
	private EmailService			emailService;

	public void sendNewPassword( String email ) throws DataNotFoundException, AuthorizationException {

		Cliente cli = clienteService.findByEmail( email );
		if ( cli == null ) {
			return;
		}

		String	newPass		= generateRandomPassword();
		String	encodedPass	= be.encode( newPass );
		cli.setSenha( encodedPass );
		clienteService.update( cli, cli.getId() );
		emailService.sendNewPassword( cli, newPass );

	}

	private String generateRandomPassword() {
		int		leftLimit			= 48;															// numeral '0'
		int		rightLimit			= 122;															// letter 'z'
		int		targetStringLength	= 10;
		Random	random				= new Random();

		String	generatedString		= random.ints( leftLimit, rightLimit + 1 )
			.filter( i -> ( i <= 57 || i >= 65 ) && ( i <= 90 || i >= 97 ) )
			.limit( targetStringLength )
			.collect( StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append )
			.toString();
		return generatedString;
	}

}
