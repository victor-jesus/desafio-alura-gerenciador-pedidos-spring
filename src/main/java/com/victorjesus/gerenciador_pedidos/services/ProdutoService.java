package com.victorjesus.gerenciador_pedidos.services;

import com.victorjesus.gerenciador_pedidos.domain.Produto;
import com.victorjesus.gerenciador_pedidos.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

public class ProdutoService implements IPersistance<Produto> {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> list(){
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public void salvar(Produto produto) {
        produtoRepository.save(produto);
    }
}
