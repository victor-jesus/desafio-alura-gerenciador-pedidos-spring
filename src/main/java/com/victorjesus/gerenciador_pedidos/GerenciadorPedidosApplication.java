package com.victorjesus.gerenciador_pedidos;

import com.victorjesus.gerenciador_pedidos.repository.CategoriaRepository;
import com.victorjesus.gerenciador_pedidos.repository.PedidoRepository;
import com.victorjesus.gerenciador_pedidos.repository.ProdutoRepository;
import com.victorjesus.gerenciador_pedidos.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		View view = new View(produtoRepository, categoriaRepository, pedidoRepository);
		view.showConsole();
	}
}
