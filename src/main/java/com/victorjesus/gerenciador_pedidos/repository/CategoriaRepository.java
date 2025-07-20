package com.victorjesus.gerenciador_pedidos.repository;

import com.victorjesus.gerenciador_pedidos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
