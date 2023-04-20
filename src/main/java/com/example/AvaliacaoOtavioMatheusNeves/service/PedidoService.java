package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Pedido;
import com.example.AvaliacaoOtavioMatheusNeves.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public <S extends Pedido> S save(S entity) {
        return pedidoRepository.save(entity);
    }

    public boolean existsById(Long id) {
        return pedidoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}
