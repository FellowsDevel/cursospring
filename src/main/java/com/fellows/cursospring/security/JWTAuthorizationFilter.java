package com.fellows.cursospring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final String	BEARER_TOKEN	= "Bearer ";

	private JWTUtil				jwtUtil;

	private UserDetailsService	detailsService;

	public JWTAuthorizationFilter( AuthenticationManager authenticationManager, JWTUtil jwtUtil,
		UserDetailsService detailsService ) {

		super( authenticationManager );
		this.jwtUtil = jwtUtil;
		this.detailsService = detailsService;
	}

	/*
	 * Verifica o token recebido para ver se é valido
	 */
	@Override
	protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain )
		throws IOException, ServletException {

		String header = request.getHeader( HttpHeaders.AUTHORIZATION );
		if ( header != null && header.startsWith( BEARER_TOKEN ) ) {
			UsernamePasswordAuthenticationToken upat = getAuthentication( header.substring( BEARER_TOKEN.length() ) );
			if ( upat != null ) {
				SecurityContextHolder.getContext().setAuthentication( upat );
			}
		}
		chain.doFilter( request, response );
	}

	private UsernamePasswordAuthenticationToken getAuthentication( String token ) {
		if ( jwtUtil.tokenValido( token ) ) {
			String		username	= jwtUtil.getUsername( token );
			UserDetails	user		= detailsService.loadUserByUsername( username );
			return new UsernamePasswordAuthenticationToken( user, null, user.getAuthorities() );
		}
		return null;
	}

}
