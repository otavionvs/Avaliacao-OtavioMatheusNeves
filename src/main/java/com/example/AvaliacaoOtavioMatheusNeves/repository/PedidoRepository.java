package com.example.AvaliacaoOtavioMatheusNeves.repository;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
