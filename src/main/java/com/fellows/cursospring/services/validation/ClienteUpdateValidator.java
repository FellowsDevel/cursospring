package com.fellows.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.dto.ClienteDTO;
import com.fellows.cursospring.repositories.ClienteRepository;
import com.fellows.cursospring.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest	request;

	@Autowired
	private ClienteRepository	repo;

	@Override
	public void initialize( ClienteUpdate constraintAnnotation ) {
		ConstraintValidator.super.initialize( constraintAnnotation );
	}

	@Override
	public boolean isValid( ClienteDTO value, ConstraintValidatorContext context ) {

		@SuppressWarnings( "unchecked" )
		Map<String, String>	mapa	= (Map<String, String>) request
				.getAttribute( HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE );
		Integer				uriId	= Integer.parseInt( mapa.get( "id" ) );

		List<FieldMessage>	list	= new ArrayList<>();
		Cliente				aux		= repo.findByEmail( value.getEmail() );

		if ( aux != null && !aux.getId().equals( uriId ) ) {
			list.add( new FieldMessage( "email", "Email já existe!" ) );
		}

		for ( FieldMessage fm : list ) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate( fm.getMessage() ).addPropertyNode( fm.getFiledName() )
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
