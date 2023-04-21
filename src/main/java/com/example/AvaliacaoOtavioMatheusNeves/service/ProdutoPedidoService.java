package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.ProdutoPedido;
import com.example.AvaliacaoOtavioMatheusNeves.repository.ProdutoPedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoPedidoService {
    private ProdutoPedidoRepository produtoPedidoRepository;

    public List<ProdutoPedido> findAll() {
        return produtoPedidoRepository.findAll();
    }

    public Optional<ProdutoPedido> findById(Long id) {
        return produtoPedidoRepository.findById(id);
    }

    public <S extends ProdutoPedido> S save(S entity) {
        return produtoPedidoRepository.save(entity);
    }

    public boolean existsById(Long id) {
        return produtoPedidoRepository.existsById(id);
    }

    public void deleteById(Long id) {
        produtoPedidoRepository.deleteById(id);
    }

}
