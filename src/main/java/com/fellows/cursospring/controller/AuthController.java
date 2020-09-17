package com.fellows.cursospring.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fellows.cursospring.dto.EmailDTO;
import com.fellows.cursospring.security.JWTUtil;
import com.fellows.cursospring.security.UserSS;
import com.fellows.cursospring.services.AuthService;
import com.fellows.cursospring.services.UserService;
import com.fellows.cursospring.services.exception.AuthorizationException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@RestController
@RequestMapping( value = "/auth" )
public class AuthController {

	@Autowired
	private JWTUtil		jwtUtil;

	@Autowired
	private AuthService	authService;

	@RequestMapping( value = "/refresh_token", method = RequestMethod.POST )
	public ResponseEntity<Void> refreshToken( HttpServletResponse response ) {
		UserSS	user	= UserService.authenticated();
		String	token	= jwtUtil.generateToken( user.getUsername() );
		response.addHeader( HttpHeaders.AUTHORIZATION, "Bearer " + token );
		return ResponseEntity.noContent().build();
	}

	@RequestMapping( value = "/forgot", method = RequestMethod.POST )
	public ResponseEntity<Void> forgotPassword( @Valid @RequestBody EmailDTO objDTO ) throws DataNotFoundException, AuthorizationException {
		authService.sendNewPassword( objDTO.getEmail() );

		return ResponseEntity.noContent().build();
	}
}
