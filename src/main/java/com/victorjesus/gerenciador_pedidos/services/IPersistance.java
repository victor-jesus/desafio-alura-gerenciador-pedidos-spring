package com.victorjesus.gerenciador_pedidos.services;

import java.util.List;

public interface IPersistance<T> {
    List<T> list();
    T salvar(T item);
}
