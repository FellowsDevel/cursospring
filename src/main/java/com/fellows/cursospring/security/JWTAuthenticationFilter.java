package com.fellows.cursospring.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellows.cursospring.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JWTUtil					jwtUtil;

	private AuthenticationManager	authenticationManager;

	public JWTAuthenticationFilter( JWTUtil jwtUtil, AuthenticationManager authenticationManager ) {
		super();
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response )
		throws AuthenticationException {

		try {
			CredenciaisDTO						cred	= new ObjectMapper().readValue( request.getInputStream(),
				CredenciaisDTO.class );
			UsernamePasswordAuthenticationToken	token	= new UsernamePasswordAuthenticationToken( cred.getEmail(),
				cred.getSenha(), new ArrayList<>() );
			Authentication						auth	= authenticationManager.authenticate( token );

			return auth;
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return super.attemptAuthentication( request, response );
	}

	@Override
	protected void successfulAuthentication(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain chain,
		Authentication authResult ) throws IOException, ServletException {

		String	username	= ( (UserSS) authResult.getPrincipal() ).getUsername();
		String	token		= jwtUtil.generateToken( username );
		response.addHeader( "Authorization", "Bearer " + token );
	}
}
