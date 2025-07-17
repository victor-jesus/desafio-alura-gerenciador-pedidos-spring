package com.victorjesus.gerenciador_pedidos.view;

import com.victorjesus.gerenciador_pedidos.domain.Categoria;
import com.victorjesus.gerenciador_pedidos.domain.Produto;
import com.victorjesus.gerenciador_pedidos.repository.CategoriaRepository;
import com.victorjesus.gerenciador_pedidos.repository.ProdutoRepository;
import com.victorjesus.gerenciador_pedidos.services.CategoriaService;
import com.victorjesus.gerenciador_pedidos.services.ProdutoService;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;

public class View {
    private ProdutoService produtoService;
    private CategoriaService categoriaService;
    Scanner scanner = new Scanner(System.in);

    public View(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoService = new ProdutoService(produtoRepository);
        this.categoriaService = new CategoriaService(categoriaRepository);
    }

    public void showConsole(){
        int option;

        do{
            String console = """
                    1 - Listar produtos do banco
                    2 - Salvar produto no banco
                    3 - Listar categorias
                    4 - Criar nova categoria
                
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
                case 3:{
                    System.out.println("Listando...");
                    listarCategorias().forEach(System.out::println);
                    break;
                }
                case 4: {
                    criarCategoria();
                    break;
                }
                case 0: {
                    System.out.println("Fechando...");
                    break;
                }
                default:
                    System.out.println("Opção invalida");
            }
        } while(option != 0);
    }

    private void criarCategoria() {
        System.out.print("Digite o nome da nova categoria: ");
        String nome = scanner.nextLine();

        categoriaService.salvar(new Categoria(nome));
        System.out.println("Categoria salva com sucesso!");
    }

    private List<Categoria> listarCategorias() {
        return categoriaService.list();
    }

    private List<Produto> getProdutos(){
        return this.produtoService.list();
    }

    private void saveProduto(){
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double precoProduto = OptionalDouble.of(Double.parseDouble(scanner.nextLine())).orElse(0.0);

        System.out.println("Categorias Disponíveis: ");
        listarCategorias().forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));

        System.out.println("Digite o Id da categoria do produto: ");
        Long categoriaId = scanner.nextLong();
        scanner.nextLine();

        var categoria = categoriaService.buscarPorId(categoriaId);

        if(categoria.isPresent()){
            Produto produto = new Produto(nomeProduto, precoProduto);
            produto.setCategoria(categoria.get());

            produtoService.salvar(produto);
            System.out.println("Produto salvo com sucesso!");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
}
