package com.fellows.cursospring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService<T> {

	public void sendOrderConfirmationEmail( T obj );

	public void sendOrderConfirmationHtmlEmail( T obj );

	public void sendEmail( SimpleMailMessage msg );

	public void sendEmail( MimeMessage msg );

}
