package com.fellows.cursospring.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fellows.cursospring.services.DBService;
import com.fellows.cursospring.services.EmailService;
import com.fellows.cursospring.services.SmtpEmailService;

@Configuration
@Profile( "dev" )
public class DevConfig {

	@Autowired
	private DBService	dbService;

	@Value( "${spring.jpa.hibernate.ddl-auto}" )
	private String		strategy;

	/*
	 * Esta configuraçao indica que todos os Beans aqui listados serão ativados apenas quando o profile "dev" estiver
	 * ativo
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if ( !strategy.equalsIgnoreCase( "create" ) ) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService<?> emailService() {
		return new SmtpEmailService();
	}

}
