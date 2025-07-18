package com.victorjesus.gerenciador_pedidos.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private LocalDate data;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pedido_produto",
    joinColumns = @JoinColumn(name = "pedido_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos = new ArrayList<>();

    public Pedido(LocalDate data, List<Produto> produtos) {
        this.data = data;
        this.produtos = produtos;
    }

    public Pedido() {
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "\nPedido: " +
                "\nNumero do pedido: " + id +
                "\nData: " + data.format(dateTimeFormatter) +
                "\nProdutos: " + produtos;
    }
}
