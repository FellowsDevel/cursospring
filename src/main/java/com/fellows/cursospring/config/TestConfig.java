package com.fellows.cursospring.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fellows.cursospring.services.DBService;
import com.fellows.cursospring.services.EmailService;
import com.fellows.cursospring.services.MockEmailService;

@Configuration
@Profile( "test" )
public class TestConfig {

	@Autowired
	private DBService dbService;

	/*
	 * Esta configuraçao indica que todos os Beans aqui listados serão ativados apenas quando o profile "test" estiver
	 * ativo
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService<?> emailService() {
		return new MockEmailService();
	}
}
