package com.fellows.cursospring.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.fellows.cursospring.domain.Cliente;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger( MockEmailService.class );

	@Override
	public void sendEmail( SimpleMailMessage msg ) {
		LOG.info( "Simulando envio de email..." );
		LOG.info( msg.toString() );
		LOG.info( "Email enviado" );
	}

	@Override
	public void sendEmail( MimeMessage msg ) {
		LOG.info( "Simulando envio de email HTML..." );
		LOG.info( msg.toString() );
		LOG.info( "Email enviado" );
	}

	@Override
	public void sendNewPassword( Cliente cli, String newPass ) {
		LOG.info( "Simulando nova senha..." );
		LOG.info( "Cliente   : " + cli.getNome() );
		LOG.info( "Nova senha: " + newPass );
		LOG.info( "Email enviado" );
	}

}
