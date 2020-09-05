package com.fellows.cursospring.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {

	private static final long	serialVersionUID	= -2044302110074934284L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer				id;

	@JsonFormat( pattern = "dd/MM/yyyy HH:mm" )
	private Date				instante;

	// cascade = CascadeType.ALL -> Necessário para nao dar problema de entidade
	// transiente
	// mappedy = "pedido" -> nome da entidade do tipo Pedido na classe de Pagamento
	@OneToOne( cascade = CascadeType.ALL, mappedBy = "pedido" )
	private Pagamento			pagamento;

	@ManyToOne
	@JoinColumn( name = "cliente_id" )
	private Cliente				cliente;

	@ManyToOne
	@JoinColumn( name = "endereco_entrega_id" ) // nome da coluna a ser criada no banco para referenciar as entidades
	private Endereco			entrega;

	@OneToMany( mappedBy = "id.pedido" )
	private Set<ItemPedido>		itens				= new HashSet<ItemPedido>();

	public Pedido() {
	}

	public Pedido( Integer id, Date instante, Cliente cliente, Endereco entrega ) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.entrega = entrega;
	}

	public double getValorTotal() {
		return itens.stream().map( x -> x.getSubtotal() ).reduce( 0.0, ( a, b ) -> a + b );
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante( Date instante ) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento( Pagamento pagamento ) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente( Cliente cliente ) {
		this.cliente = cliente;
	}

	public Endereco getEntrega() {
		return entrega;
	}

	public void setEntrega( Endereco entrega ) {
		this.entrega = entrega;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens( Set<ItemPedido> itens ) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", instante=" + instante + ", pagamento=" + pagamento + ", cliente=" + cliente
				+ ", entrega=" + entrega + "]";
	}

	@Override
	public int hashCode() {
		final int	prime	= 31;
		int			result	= 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		Pedido other = (Pedido) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}
