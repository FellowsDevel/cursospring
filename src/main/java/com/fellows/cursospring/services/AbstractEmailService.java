package com.fellows.cursospring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value( "${default.sender}" )
	private String			sender;

	@Autowired
	private TemplateEngine	templateEngine;

	@Autowired
	private JavaMailSender	javaMailSender;

	@Override
	public void sendOrderConfirmationEmail( Pedido obj ) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido( obj );
		sendEmail( sm );
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido( Pedido obj ) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo( obj.getCliente().getEmail() );
		sm.setFrom( sender );
		sm.setSubject( "Pedido confirmado! Código: " + obj.getId() );
		sm.setSentDate( new Date( System.currentTimeMillis() ) );
		sm.setText( obj.toString() );
		return sm;
	}

	@Override
	public void sendOrderConfirmationHtmlEmail( Pedido obj ) {
		try {
			MimeMessage mm = prepareMimeMailMessageFromPedido( obj );
			sendEmail( mm );
		} catch ( MessagingException e ) {
			sendOrderConfirmationEmail( obj );
		}
	}

	protected MimeMessage prepareMimeMailMessageFromPedido( Pedido obj ) throws MessagingException {
		MimeMessage			mm	= javaMailSender.createMimeMessage();
		MimeMessageHelper	mmh	= new MimeMessageHelper( mm, true );
		mmh.setTo( obj.getCliente().getEmail() );
		mmh.setFrom( sender );
		mmh.setSubject( "Pedido confirmado! Código: " + obj.getId() );
		mmh.setSentDate( new Date( System.currentTimeMillis() ) );
		mmh.setText( htmlFromTemplatePedido( obj ), true );
		return mm;
	}

	protected String htmlFromTemplatePedido( Pedido obj ) {
		Context ctx = new Context();
		ctx.setVariable( "pedido", obj );
		return templateEngine.process( "email/confirmacaoPedido", ctx );
	}

	@Override
	public void sendNewPassword( Cliente cli, String newPass ) {
		SimpleMailMessage sm = prepareNewPasswordEmail( cli, newPass );
		sendEmail( sm );
	}

	private SimpleMailMessage prepareNewPasswordEmail( Cliente cli, String newPass ) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo( cli.getEmail() );
		sm.setFrom( sender );
		sm.setSubject( "Solicitaçao de nova senha" );
		sm.setSentDate( new Date( System.currentTimeMillis() ) );
		sm.setText( "Nova senha: " + newPass );
		return sm;
	}
}
