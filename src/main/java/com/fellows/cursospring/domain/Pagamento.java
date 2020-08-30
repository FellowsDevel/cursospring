package com.fellows.cursospring.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fellows.cursospring.domain.enums.EstadoPagamento;

@Entity

/*
 * A Anotaçao abaixo faz com que o mapemento das entidades filhas de Pagamento sejam mescladas em uma única tabela.
 */
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class Pagamento implements Serializable {

	private static final long	serialVersionUID	= -8640319999223883298L;

	@Id
	private Integer				id;
	private Integer				estadoPagamento;

	@JsonIgnore
	/*
	 * Essas anotações fazem com que essa classe Pagamento tenha a mesma chave "ÌD" do pedido
	 */
	@OneToOne
	@JoinColumn( name = "pedido_id" )
	@MapsId
	private Pedido				pedido;

	public Pagamento() {
	}

	public Pagamento( Integer id, EstadoPagamento estadoPagamento, Pedido pedido ) {
		super();
		this.id = id;
		this.estadoPagamento = estadoPagamento == null ? null : estadoPagamento.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum( estadoPagamento );
	}

	public void setEstadoPagamento( EstadoPagamento estadoPagamento ) {
		this.estadoPagamento = estadoPagamento.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido( Pedido pedido ) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", estadoPagamento=" + estadoPagamento + ", pedido=" + pedido + "]";
	}

	@Override
	public int hashCode() {
		final int	prime	= 31;
		int			result	= 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( pedido == null ) ? 0 : pedido.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Pagamento other = (Pagamento) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( pedido == null ) {
			if ( other.pedido != null )
				return false;
		} else if ( !pedido.equals( other.pedido ) )
			return false;
		return true;
	}

}
