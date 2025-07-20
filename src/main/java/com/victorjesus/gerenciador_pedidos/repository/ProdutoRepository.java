package com.victorjesus.gerenciador_pedidos.repository;

import com.victorjesus.gerenciador_pedidos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
