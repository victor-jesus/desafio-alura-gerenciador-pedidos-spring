package com.victorjesus.gerenciador_pedidos.repository;

import com.victorjesus.gerenciador_pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
