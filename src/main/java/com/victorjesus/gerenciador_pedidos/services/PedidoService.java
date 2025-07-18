package com.victorjesus.gerenciador_pedidos.services;

import com.victorjesus.gerenciador_pedidos.domain.Pedido;
import com.victorjesus.gerenciador_pedidos.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

public class PedidoService implements IPersistance<Pedido> {
    PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    @Override
    public void salvar(Pedido item) {
        pedidoRepository.save(item);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}
