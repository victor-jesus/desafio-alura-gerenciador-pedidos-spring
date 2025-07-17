package com.victorjesus.gerenciador_pedidos.services;

import com.victorjesus.gerenciador_pedidos.domain.Categoria;

import java.util.List;
import java.util.Optional;

public interface IPersistance<T> {
    List<T> list();
    T salvar(T item);
    Optional<T> buscarPorId(Long id);
}
