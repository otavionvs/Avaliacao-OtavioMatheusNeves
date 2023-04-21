package com.example.AvaliacaoOtavioMatheusNeves.repository;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Boolean existsByEmail(String Email);
}
