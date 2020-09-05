package com.fellows.cursospring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fellows.cursospring.domain.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto( PagamentoBoleto pgt, Date instante ) {
		Calendar cal = Calendar.getInstance();
		cal.setTime( instante );
		cal.add( Calendar.DAY_OF_MONTH, 7 );
		pgt.setVencimento( cal.getTime() );
	}

}
