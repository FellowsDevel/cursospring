package com.fellows.cursospring.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler( DataNotFoundException.class )
	public ResponseEntity<StandardError> dataNotFound( DataNotFoundException e, HttpServletRequest request ) {
		StandardError error = new StandardError( HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis() );
		return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( error );
	}

	@ExceptionHandler( DataIntegrityException.class )
	public ResponseEntity<StandardError> dataIntegrity( DataIntegrityException e, HttpServletRequest request ) {
		StandardError error = new StandardError( HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis() );
		return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( error );
	}

	@ExceptionHandler( MethodArgumentNotValidException.class )
	public ResponseEntity<StandardError> argumentNotValid(
			MethodArgumentNotValidException e,
			HttpServletRequest request ) {
		ValidationError error = new ValidationError( HttpStatus.BAD_REQUEST.value(), "Erro de validação",
				System.currentTimeMillis() );
		e.getBindingResult().getFieldErrors().stream().forEach( ( err ) -> {
			error.addMessge( err.getField(), err.getDefaultMessage() );
		} );
		return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( error );
	}
}
