package com.example.AvaliacaoOtavioMatheusNeves.repository;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
