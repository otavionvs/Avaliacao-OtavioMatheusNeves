package com.example.AvaliacaoOtavioMatheusNeves.repository;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
}
