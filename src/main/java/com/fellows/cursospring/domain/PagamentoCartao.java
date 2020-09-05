package com.fellows.cursospring.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fellows.cursospring.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName( "pagamentoCartao" )
public class PagamentoCartao extends Pagamento {

	private static final long	serialVersionUID	= -254684891976590123L;

	private Integer				parcelas;

	public PagamentoCartao() {
	}

	public PagamentoCartao( Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer parcelas ) {
		super( id, estadoPagamento, pedido );
		this.parcelas = parcelas;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas( Integer parcelas ) {
		this.parcelas = parcelas;
	}

	@Override
	public String toString() {
		return "PagamentoCartao [parcelas=" + parcelas + "]";
	}

	@Override
	public int hashCode() {
		final int	prime	= 31;
		int			result	= super.hashCode();
		result = prime * result + ( ( parcelas == null ) ? 0 : parcelas.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( !super.equals( obj ) )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		PagamentoCartao other = (PagamentoCartao) obj;
		if ( parcelas == null ) {
			if ( other.parcelas != null )
				return false;
		} else if ( !parcelas.equals( other.parcelas ) )
			return false;
		return true;
	}

}
