package com.fellows.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fellows.cursospring.domain.enums.TipoCliente;
import com.fellows.cursospring.dto.ClienteNewDTO;
import com.fellows.cursospring.resources.exceptions.FieldMessage;
import com.fellows.cursospring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

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
		for ( FieldMessage fm : list ) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate( fm.getMessage() ).addPropertyNode( fm.getFiledName() )
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
