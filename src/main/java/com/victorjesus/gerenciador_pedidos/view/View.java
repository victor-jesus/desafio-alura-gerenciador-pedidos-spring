package com.victorjesus.gerenciador_pedidos.view;

import com.victorjesus.gerenciador_pedidos.domain.Categoria;
import com.victorjesus.gerenciador_pedidos.domain.Fornecedor;
import com.victorjesus.gerenciador_pedidos.domain.Pedido;
import com.victorjesus.gerenciador_pedidos.domain.Produto;
import com.victorjesus.gerenciador_pedidos.repository.CategoriaRepository;
import com.victorjesus.gerenciador_pedidos.repository.FornecedorRepository;
import com.victorjesus.gerenciador_pedidos.repository.PedidoRepository;
import com.victorjesus.gerenciador_pedidos.repository.ProdutoRepository;
import com.victorjesus.gerenciador_pedidos.services.CategoriaService;
import com.victorjesus.gerenciador_pedidos.services.FornecedorService;
import com.victorjesus.gerenciador_pedidos.services.PedidoService;
import com.victorjesus.gerenciador_pedidos.services.ProdutoService;

import java.time.LocalDate;
import java.util.*;

public class View {
    private ProdutoService produtoService;
    private CategoriaService categoriaService;
    private PedidoService pedidoService;
    private FornecedorService fornecedorService;
    Scanner scanner = new Scanner(System.in);

    public View(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, PedidoRepository pedidoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoService = new ProdutoService(produtoRepository);
        this.categoriaService = new CategoriaService(categoriaRepository);
        this.pedidoService = new PedidoService(pedidoRepository);
        this.fornecedorService = new FornecedorService(fornecedorRepository);
    }

    public void showConsole(){
        int option;

        do{
            String console = """
                    
                    1 - Listar produtos do banco
                    2 - Salvar produto no banco
                    3 - Listar categorias
                    4 - Criar nova categoria
                    5 - Criar pedido
                    6 - Listar pedido
                    7 - Salvar fornecedor
                    8 - Listar fornecedores
                
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
                case 5: {
                    criarPedido();
                    break;
                }
                case 6: {
                    listarPedidos().forEach(System.out::println);
                    break;
                }
                case 7: {
                    salvarFornecedor();
                    break;
                }
                case 8: {
                    listarFornecedores().forEach(System.out::println);
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

    private List<Fornecedor> listarFornecedores() {
        return fornecedorService.list();
    }

    private void salvarFornecedor() {
        System.out.print("Digite o nome do fornecedor: ");
        String nomeFornecedor = scanner.nextLine();

        fornecedorService.salvar(new Fornecedor(nomeFornecedor));
        System.out.println("--- Fornecedor salvo ---");
    }

    private List<Pedido> listarPedidos() {
        System.out.println("--- Listando ---");
        return pedidoService.list();
    }

    private void criarPedido() {
        System.out.println("----");
        getProdutos().forEach(p -> System.out.println(" ID: " + p.getId() + " - " + p.getNome() + " - " + p.getCategoria().getNome() + " (" + p.getPreco() + ")"));
        System.out.println("Digite os Ids dos produtos (Separados por virgula): ");
        var idsProdutos = scanner.nextLine();

        var ArrIds = idsProdutos.split(",");

        List<Produto> produtosPedido = new ArrayList<>();
        for(String id: ArrIds){
            Optional<Produto> produto = produtoService.buscarPorId(Long.parseLong(id.trim()));
            produto.ifPresentOrElse(p -> {
                produtosPedido.add(p);
                System.out.println("Salvando " + p.getNome() + " no pedido.");
            }, () -> System.out.println("Produto de Id " + id + " não encontrado"));
        }

        if(!produtosPedido.isEmpty()){
            Pedido pedido = new Pedido(LocalDate.now(), produtosPedido);
            pedidoService.salvar(pedido);
            System.out.println("--- Pedido salvo com sucesso ---");
        } else {
            System.out.println("Não há produtos no seu pedido.");
        }
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

        System.out.println("Fornecedores disponíveis: ");
        listarFornecedores().forEach(f -> System.out.println(f.getId() + " - " + f.getNome()));

        System.out.println("Digite o Id do fornecedor do produto: ");
        Long fornecedorId = scanner.nextLong();
        scanner.nextLine();

        var categoria = categoriaService.buscarPorId(categoriaId);
        var fornecedor = fornecedorService.buscarPorId(fornecedorId);

        if(categoria.isPresent() && fornecedor.isPresent()){
            Produto produto = new Produto(nomeProduto, precoProduto, fornecedor.get());
            produto.setCategoria(categoria.get());

            produtoService.salvar(produto);
            System.out.println("Produto salvo com sucesso!");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
}
