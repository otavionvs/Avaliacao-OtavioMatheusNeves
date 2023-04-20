package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Fornecedor;
import com.example.AvaliacaoOtavioMatheusNeves.repository.FornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FornecedorService {
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public <S extends Fornecedor> S save(S entity) {
        return fornecedorRepository.save(entity);
    }

    public boolean existsById(Long id) {
        return fornecedorRepository.existsById(id);
    }

    public void deleteById(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
