package com.fellows.cursospring;

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
import com.fellows.cursospring.domain.Produto;
import com.fellows.cursospring.domain.enums.TipoCliente;
import com.fellows.cursospring.repositories.CategoriaRepository;
import com.fellows.cursospring.repositories.CidadeRepository;
import com.fellows.cursospring.repositories.ClienteRepository;
import com.fellows.cursospring.repositories.EnderecoRepository;
import com.fellows.cursospring.repositories.EstadoRepository;
import com.fellows.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Recife", est1);
		Cidade cid2 = new Cidade(null, "Olinda", est1);
		Cidade cid3 = new Cidade(null, "Governador Valadares", est2);
		Cidade cid4 = new Cidade(null, "São Paulo", est2);
		Cidade cid5 = new Cidade(null, "Ribeirão Preto", est2);

		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		est2.getCidades().addAll(Arrays.asList(cid3, cid4, cid5));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4, cid5));
		
		
		Cliente c1 = new Cliente(null, "André", "email@email.com", "0221271950", TipoCliente.PESSOAFISICA);
		c1.getTelefones().addAll(Arrays.asList("123", "222"));
		
		Endereco e1 = new Endereco(null, "Rua daqui", "123", "em casa", "Casa Amarela", "52070080", c1, cid1);
		Endereco e2 = new Endereco(null, "Rua matos", "33", "ap 22", "Torre", "52333080", c1, cid4);
		
		c1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(c1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}

}
