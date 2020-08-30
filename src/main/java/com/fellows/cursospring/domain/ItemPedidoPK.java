package com.fellows.cursospring.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * Classe definindo uma PK a ser utilizada em tabela associativa
 * 
 * Esse tipo de classe nesse contexto, não precisa de construtor
 * 
 * 
 * A Anotaçao @Embeddable informa que essa classe pode ser utilizada como subtipo
 */
@Embeddable
public class ItemPedidoPK implements Serializable {

	private static final long	serialVersionUID	= -3353169514817205128L;

	@ManyToOne
	@JoinColumn( name = "pedido_id" ) // nome da coluna na tabela
	private Pedido				pedido;

	@ManyToOne
	@JoinColumn( name = "produto_id" ) // nome da coluna na tabela
	private Produto				produto;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido( Pedido pedido ) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto( Produto produto ) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemPedidoPK [pedido=" + pedido + ", produto=" + produto + "]";
	}

	@Override
	public int hashCode() {
		final int	prime	= 31;
		int			result	= 1;
		result = prime * result + ( ( pedido == null ) ? 0 : pedido.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
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
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if ( pedido == null ) {
			if ( other.pedido != null )
				return false;
		} else if ( !pedido.equals( other.pedido ) )
			return false;
		if ( produto == null ) {
			if ( other.produto != null )
				return false;
		} else if ( !produto.equals( other.produto ) )
			return false;
		return true;
	}

}
