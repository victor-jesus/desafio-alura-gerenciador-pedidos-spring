package com.victorjesus.gerenciador_pedidos.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Fornecedor() {
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

    @Override
    public String toString() {
        return "---Fornecedor ---- " +
                "\nId:" + id +
                "\nNome: '" + nome + '\'';
    }
}
