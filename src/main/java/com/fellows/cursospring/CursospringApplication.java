package com.fellows.cursospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fellows.cursospring.domain.Categoria;
import com.fellows.cursospring.domain.Cidade;
import com.fellows.cursospring.domain.Cliente;
import com.fellows.cursospring.domain.Endereco;
import com.fellows.cursospring.domain.Estado;
import com.fellows.cursospring.domain.ItemPedido;
import com.fellows.cursospring.domain.Pagamento;
import com.fellows.cursospring.domain.PagamentoBoleto;
import com.fellows.cursospring.domain.PagamentoCartao;
import com.fellows.cursospring.domain.Pedido;
import com.fellows.cursospring.domain.Produto;
import com.fellows.cursospring.domain.enums.EstadoPagamento;
import com.fellows.cursospring.domain.enums.TipoCliente;
import com.fellows.cursospring.repositories.CategoriaRepository;
import com.fellows.cursospring.repositories.CidadeRepository;
import com.fellows.cursospring.repositories.ClienteRepository;
import com.fellows.cursospring.repositories.EnderecoRepository;
import com.fellows.cursospring.repositories.EstadoRepository;
import com.fellows.cursospring.repositories.ItemPedidoRepository;
import com.fellows.cursospring.repositories.PagamentoRepository;
import com.fellows.cursospring.repositories.PedidoRepository;
import com.fellows.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository		categoriaRepository;

	@Autowired
	private ProdutoRepository		produtoRepository;

	@Autowired
	private EstadoRepository		estadoRepository;

	@Autowired
	private CidadeRepository		cidadeRepository;

	@Autowired
	private ClienteRepository		clienteRepository;

	@Autowired
	private EnderecoRepository		enderecoRepository;

	@Autowired
	private PedidoRepository		pedidoRepository;

	@Autowired
	private PagamentoRepository		pagamentoRepository;

	@Autowired
	private ItemPedidoRepository	itemPedidoRepository;

	public static void main( String[] args ) {
		SpringApplication.run( CursospringApplication.class, args );
	}

	@Override
	public void run( String... args ) throws Exception {
		Categoria	cat1	= new Categoria( null, "Informática" );
		Categoria	cat2	= new Categoria( null, "Escritório" );
		Categoria	cat3	= new Categoria( null, "Cama mesa e banho" );
		Categoria	cat4	= new Categoria( null, "Eletrônicos" );
		Categoria	cat5	= new Categoria( null, "Jardinagem" );
		Categoria	cat6	= new Categoria( null, "Decoração" );
		Categoria	cat7	= new Categoria( null, "Perfumaria" );

		Produto		p1		= new Produto( null, "Computador", 2000.00 );
		Produto		p2		= new Produto( null, "Impressora", 800.00 );
		Produto		p3		= new Produto( null, "Mouse", 80.00 );
		Produto		p4		= new Produto( null, "Mesa de escritrio", 300.00 );
		Produto		p5		= new Produto( null, "Toalha", 50.00 );
		Produto		p6		= new Produto( null, "Colcha", 200.00 );
		Produto		p7		= new Produto( null, "TV True Color", 1200.00 );
		Produto		p8		= new Produto( null, "Roçadeira", 800.00 );
		Produto		p9		= new Produto( null, "Abajour", 100.00 );
		Produto		p10		= new Produto( null, "Pendene", 180.00 );
		Produto		p11		= new Produto( null, "Shampoo", 90.00 );

		cat1.getProdutos().addAll( Arrays.asList( p1, p2, p3 ) );
		cat2.getProdutos().addAll( Arrays.asList( p2, p4 ) );
		cat3.getProdutos().addAll( Arrays.asList( p5, p6 ) );
		cat4.getProdutos().addAll( Arrays.asList( p1, p2, p3, p7 ) );
		cat5.getProdutos().addAll( Arrays.asList( p8 ) );
		cat6.getProdutos().addAll( Arrays.asList( p9, p10 ) );
		cat7.getProdutos().addAll( Arrays.asList( p11 ) );

		p1.getCategorias().add( cat1 );
		p2.getCategorias().addAll( Arrays.asList( cat1, cat2 ) );
		p3.getCategorias().add( cat1 );
		p4.getCategorias().add( cat2 );
		p5.getCategorias().add( cat3 );
		p6.getCategorias().add( cat3 );
		p7.getCategorias().add( cat4 );
		p8.getCategorias().add( cat5 );
		p9.getCategorias().add( cat6 );
		p10.getCategorias().add( cat6 );
		p11.getCategorias().add( cat7 );
		

		categoriaRepository.saveAll( Arrays.asList( cat1, cat2, cat3, cat4, cat5, cat6, cat7 ) );
		produtoRepository.saveAll( Arrays.asList( p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11 ) );

		Estado	est1	= new Estado( null, "Pernambuco" );
		Estado	est2	= new Estado( null, "São Paulo" );

		Cidade	cid1	= new Cidade( null, "Recife", est1 );
		Cidade	cid2	= new Cidade( null, "Olinda", est1 );
		Cidade	cid3	= new Cidade( null, "Governador Valadares", est2 );
		Cidade	cid4	= new Cidade( null, "São Paulo", est2 );
		Cidade	cid5	= new Cidade( null, "Ribeirão Preto", est2 );

		est1.getCidades().addAll( Arrays.asList( cid1, cid2 ) );
		est2.getCidades().addAll( Arrays.asList( cid3, cid4, cid5 ) );

		estadoRepository.saveAll( Arrays.asList( est1, est2 ) );
		cidadeRepository.saveAll( Arrays.asList( cid1, cid2, cid3, cid4, cid5 ) );

		Cliente c1 = new Cliente( null, "André", "email@email.com", "0221271950", TipoCliente.PESSOAFISICA );
		c1.getTelefones().addAll( Arrays.asList( "123", "222" ) );

		Endereco	e1	= new Endereco( null, "Rua daqui", "123", "em casa", "Casa Amarela", "52070080", c1, cid1 );
		Endereco	e2	= new Endereco( null, "Rua matos", "33", "ap 22", "Torre", "52333080", c1, cid4 );

		c1.getEnderecos().addAll( Arrays.asList( e1, e2 ) );

		clienteRepository.saveAll( Arrays.asList( c1 ) );
		enderecoRepository.saveAll( Arrays.asList( e1, e2 ) );

		SimpleDateFormat	sdf		= new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
		Pedido				ped1	= new Pedido( null, sdf.parse( "30/09/2019 12:32" ), c1, e1 );
		Pedido				ped2	= new Pedido( null, sdf.parse( "10/10/2019 12:32" ), c1, e2 );

		Pagamento			pag1	= new PagamentoCartao( null, EstadoPagamento.QUITADO, ped1, 6 );
		ped1.setPagamento( pag1 );

		Pagamento pag2 = new PagamentoBoleto( null, EstadoPagamento.PENDENTE, ped2, sdf.parse( "20/10/2019 11:22" ),
				null );
		ped2.setPagamento( pag2 );

		c1.getPedidos().addAll( Arrays.asList( ped1, ped2 ) );

		pedidoRepository.saveAll( Arrays.asList( ped1, ped2 ) );
		pagamentoRepository.saveAll( Arrays.asList( pag1, pag2 ) );

		ItemPedido	ip1	= new ItemPedido( ped1, p1, 0.0, 1, 2000.0 );
		ItemPedido	ip2	= new ItemPedido( ped1, p3, 0.0, 2, 80.0 );
		ItemPedido	ip3	= new ItemPedido( ped2, p2, 100.0, 1, 800.0 );

		ped1.getItens().addAll( Arrays.asList( ip1, ip2 ) );
		ped2.getItens().addAll( Arrays.asList( ip3 ) );

		p1.getItens().addAll( Arrays.asList( ip1 ) );
		p2.getItens().addAll( Arrays.asList( ip3 ) );
		p3.getItens().addAll( Arrays.asList( ip2 ) );

		itemPedidoRepository.saveAll( Arrays.asList( ip1, ip2, ip3 ) );
	}

}
