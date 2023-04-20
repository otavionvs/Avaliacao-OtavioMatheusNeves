package com.example.AvaliacaoOtavioMatheusNeves.repository;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.EnderecoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoEntregaRepository extends JpaRepository<EnderecoEntrega, Long> {
}
