package com.fellows.cursospring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.domain.Pedido;

public interface EmailService {

	public void sendOrderConfirmationEmail( Pedido obj );

	public void sendOrderConfirmationHtmlEmail( Pedido obj );

	public void sendEmail( SimpleMailMessage msg );

	public void sendEmail( MimeMessage msg );

	public void sendNewPassword( Cliente cli, String newPass );

}
