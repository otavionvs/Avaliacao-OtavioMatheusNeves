package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import com.example.AvaliacaoOtavioMatheusNeves.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public <S extends Endereco> S save(S entity) {
        return enderecoRepository.save(entity);
    }

    public boolean existsById(Long id) {
        return enderecoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
