package com.example.AvaliacaoOtavioMatheusNeves.service;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Cliente;
import com.example.AvaliacaoOtavioMatheusNeves.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public <S extends Cliente> S save(S entity) {
        return clienteRepository.save(entity);
    }

    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    public Boolean existsByEmail(String Email) {
        return clienteRepository.existsByEmail(Email);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
