package com.victorjesus.gerenciador_pedidos.view;

import com.victorjesus.gerenciador_pedidos.domain.Produto;
import com.victorjesus.gerenciador_pedidos.repository.ProdutoRepository;
import com.victorjesus.gerenciador_pedidos.services.ProdutoService;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;

public class View {
    ProdutoService produtoService;
    Scanner scanner = new Scanner(System.in);

    public View(ProdutoRepository produtoRepository) {
        this.produtoService = new ProdutoService(produtoRepository);
    }

    public void showConsole(){
        int option;

        do{
            String console = """
                    1 - Listar produtos do banco
                    2 - Salvar produto no banco
                
                    0 - Sair
                """;
            System.out.print(console);
            option = scanner.nextInt();
            scanner.nextLine();

            switch(option){
                case 1: {
                    System.out.println("Listando...");
                    getProdutos().forEach(System.out::println);
                    break;
                }
                case 2: {
                    saveProduto();
                    System.out.println("Salvando...");
                    break;
                }
                default:
                    System.out.println("Opção invalida");
            }
        } while(option != 0);
    }

    private List<Produto> getProdutos(){
        return this.produtoService.list();
    }

    private void saveProduto(){
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double precoProduto = OptionalDouble.of(Double.parseDouble(scanner.nextLine())).orElse(0.0);

        Produto produto = new Produto(nomeProduto, precoProduto);

        this.produtoService.salvar(produto);
    }
}
