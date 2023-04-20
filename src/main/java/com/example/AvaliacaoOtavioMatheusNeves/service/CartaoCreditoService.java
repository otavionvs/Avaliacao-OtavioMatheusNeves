package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.CartaoCredito;
import com.example.AvaliacaoOtavioMatheusNeves.repository.CartaoCreditoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartaoCreditoService {
    private CartaoCreditoRepository cartaoCreditoRepository;

    public List<CartaoCredito> findAll() {
        return cartaoCreditoRepository.findAll();
    }

    public Optional<CartaoCredito> findById(Long id) {
        return cartaoCreditoRepository.findById(id);
    }

    public <S extends CartaoCredito> S save(S entity) {
        return cartaoCreditoRepository.save(entity);
    }

    public boolean existsById(Long id) {
        return cartaoCreditoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        cartaoCreditoRepository.deleteById(id);
    }
}
