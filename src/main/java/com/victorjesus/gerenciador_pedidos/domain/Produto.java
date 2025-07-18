package com.victorjesus.gerenciador_pedidos.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column
    private String nome;
    @Column(name = "valor")
    private Double preco;
    @ManyToOne
    private Categoria categoria;
    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Produto(String nome, Double preco, Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Produto(){}

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "\nProduto: " +
                "\nId: '" + id + '\'' +
                "\nNome: '" + nome + '\'' +
                "\nPreco: " + preco + "\'" +
                "\nCategoria: " + categoria.getNome() +
                "\nFornecedor: " + fornecedor.getNome();
    }
}
