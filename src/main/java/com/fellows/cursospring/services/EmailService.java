package com.fellows.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService<T> {

	public void sendOrderConfirmationEmail( T obj );

	public void sendEmail( SimpleMailMessage msg );

}
