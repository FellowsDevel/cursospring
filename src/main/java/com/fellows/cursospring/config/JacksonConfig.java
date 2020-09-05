package com.fellows.cursospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellows.cursospring.domain.PagamentoBoleto;
import com.fellows.cursospring.domain.PagamentoCartao;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure( ObjectMapper objectMapper ) {
				objectMapper.registerSubtypes( PagamentoBoleto.class );
				objectMapper.registerSubtypes( PagamentoCartao.class );
				super.configure( objectMapper );
			}
		};
		return builder;
	}
}
