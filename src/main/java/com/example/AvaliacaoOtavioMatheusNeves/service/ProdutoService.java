package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Produto;
import com.example.AvaliacaoOtavioMatheusNeves.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public <S extends Produto> S save(S id) {
        return produtoRepository.save(id);
    }

    public boolean existsById(Long id) {
        return produtoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}
