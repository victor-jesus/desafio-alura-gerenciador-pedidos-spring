package com.victorjesus.gerenciador_pedidos.services;

import java.util.List;
import java.util.Optional;

public interface IPersistance<T> {
    List<T> list();
    void salvar(T item);
    Optional<T> buscarPorId(Long id);
}
