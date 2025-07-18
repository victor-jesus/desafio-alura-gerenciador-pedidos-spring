package com.victorjesus.gerenciador_pedidos.services;

import com.victorjesus.gerenciador_pedidos.domain.Fornecedor;
import com.victorjesus.gerenciador_pedidos.repository.FornecedorRepository;

import java.util.List;
import java.util.Optional;

public class FornecedorService implements IPersistance<Fornecedor> {
    FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public List<Fornecedor> list() {
        return fornecedorRepository.findAll();
    }

    @Override
    public void salvar(Fornecedor item) {
        fornecedorRepository.save(item);
    }

    @Override
    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }
}
