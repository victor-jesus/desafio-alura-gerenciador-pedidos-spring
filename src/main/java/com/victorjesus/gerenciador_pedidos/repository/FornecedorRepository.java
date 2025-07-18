package com.victorjesus.gerenciador_pedidos.repository;

import com.victorjesus.gerenciador_pedidos.domain.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
