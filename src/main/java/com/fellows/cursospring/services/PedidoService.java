package com.fellows.cursospring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fellows.cursospring.domain.ItemPedido;
import com.fellows.cursospring.domain.PagamentoBoleto;
import com.fellows.cursospring.domain.Pedido;
import com.fellows.cursospring.domain.enums.EstadoPagamento;
import com.fellows.cursospring.repositories.ItemPedidoRepository;
import com.fellows.cursospring.repositories.PagamentoRepository;
import com.fellows.cursospring.repositories.PedidoRepository;
import com.fellows.cursospring.services.exception.DataIntegrityException;
import com.fellows.cursospring.services.exception.DataNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository		repo;

	@Autowired
	private BoletoService			boletoService;

	@Autowired
	private PagamentoRepository		pagamentoRepository;

	@Autowired
	private ProdutoService			produtoService;

	@Autowired
	private ItemPedidoRepository	itemPedidoRepository;

	@Autowired
	private ClienteService			clienteService;

	public Pedido find( Integer id ) throws DataNotFoundException {
		Optional<Pedido> obj = repo.findById( id );
		return obj.orElseThrow( () -> new DataNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName() ) );
	}

	@Transactional
	public Pedido insert( Pedido obj ) throws DataNotFoundException {
		obj.setId( null );
		obj.setInstante( new Date() );
		obj.setCliente( clienteService.find( obj.getCliente().getId() ) );
		obj.getPagamento().setEstadoPagamento( EstadoPagamento.PENDENTE );
		obj.getPagamento().setPedido( obj );
		if ( obj.getPagamento() instanceof PagamentoBoleto ) {
			PagamentoBoleto pgt = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto( pgt, obj.getInstante() );
		}
		obj = repo.save( obj );
		pagamentoRepository.save( obj.getPagamento() );

		for ( ItemPedido ip : obj.getItens() ) {
			try {
				ip.setDesconto( 0.0 );
				ip.setProduto( produtoService.find( ip.getProduto().getId() ) );
				ip.setPreco( ip.getProduto().getPreco() );
				ip.setPedido( obj );
			} catch ( DataNotFoundException e ) {
			}
		}

		itemPedidoRepository.saveAll( obj.getItens() );
		System.out.println( obj );
		return obj;
	}

	public Pedido update( Pedido obj, Integer id ) throws DataNotFoundException {
		find( id );
		return repo.save( obj );
	}

	public void delete( Integer id ) throws DataIntegrityException {
		try {
			repo.deleteById( id );
		} catch ( DataIntegrityViolationException e ) {
			throw new DataIntegrityException( e.getMessage() );
		}
	}
}
