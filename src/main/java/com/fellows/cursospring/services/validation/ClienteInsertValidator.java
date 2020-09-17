package com.fellows.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fellows.cursospring.controller.exceptions.FieldMessage;
import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.domain.enums.TipoCliente;
import com.fellows.cursospring.dto.ClienteNewDTO;
import com.fellows.cursospring.repositories.ClienteRepository;
import com.fellows.cursospring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize( ClienteInsert constraintAnnotation ) {
		ConstraintValidator.super.initialize( constraintAnnotation );
	}

	@Override
	public boolean isValid( ClienteNewDTO value, ConstraintValidatorContext context ) {
		List<FieldMessage> list = new ArrayList<>();

		if ( value.getTipo().equals( TipoCliente.PESSOAFISICA.getCod() ) ) {
			if ( !BR.isValidCPF( value.getCpfOuCnpj() ) ) {
				list.add( new FieldMessage( "CPF", "CPF inválido" ) );
			}
		} else {
			if ( !BR.isValidCNPJ( value.getCpfOuCnpj() ) ) {
				list.add( new FieldMessage( "CNPJ", "CNPJ inválido" ) );
			}
		}

		Cliente aux = repo.findByEmail( value.getEmail() );
		if ( aux != null ) {
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
