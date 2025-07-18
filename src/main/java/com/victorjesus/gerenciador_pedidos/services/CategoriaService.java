package com.victorjesus.gerenciador_pedidos.services;

import com.victorjesus.gerenciador_pedidos.domain.Categoria;
import com.victorjesus.gerenciador_pedidos.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

public class CategoriaService implements IPersistance<Categoria> {
    CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> list() {
        return categoriaRepository.findAll();
    }
    
    @Override
    public Optional<Categoria> buscarPorId(Long id){
        return categoriaRepository.findById(id);
    }

    @Override
    public void salvar(Categoria item) {
        categoriaRepository.save(item);
    }
}

