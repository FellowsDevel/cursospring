package com.fellows.cursospring.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value( "${jwt.secret}" )
	private String	secret;

	@Value( "${jwt.expiration}" )
	private Long	expiration;

	public JWTUtil() {
		// TODO Auto-generated constructor stub
	}

	public String getSecret() {
		return secret;
	}

	public Long getExpiration() {
		return expiration;
	}

	public String generateToken( String username ) {
		return Jwts.builder()
				.setSubject( username ).setExpiration( new Date( System.currentTimeMillis() + expiration ) )
				.signWith( SignatureAlgorithm.HS512, secret.getBytes() ).compact();
	}

}
