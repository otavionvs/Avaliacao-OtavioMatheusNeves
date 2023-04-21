package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.ClienteDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.dto.ClienteUpdateDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.dto.EnderecoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.CartaoCredito;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Cliente;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import com.example.AvaliacaoOtavioMatheusNeves.service.CartaoCreditoService;
import com.example.AvaliacaoOtavioMatheusNeves.service.ClienteService;
import com.example.AvaliacaoOtavioMatheusNeves.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/prova/cliente")
@AllArgsConstructor
public class ClienteController {
    private ClienteService clienteService;
    private EnderecoService enderecoService;
    private CartaoCreditoService cartaoCreditoService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if(clienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        return ResponseEntity.ok(clienteOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ClienteDTO clienteDTO) {
        if(clienteService.existsByEmail(clienteDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já em uso!");
        }
        Cliente cliente = new Cliente();
        Endereco endereco;
        List<Endereco> enderecos = new ArrayList<>();
        CartaoCredito cartaoCredito = new CartaoCredito();
        BeanUtils.copyProperties(clienteDTO, cliente);
        BeanUtils.copyProperties(clienteDTO.getCartao(), cartaoCredito);
        for(EnderecoDTO enderecoDTO : clienteDTO.getEnderecos()){
            endereco = new Endereco();
            BeanUtils.copyProperties(enderecoDTO, endereco);
            enderecos.add(endereco);
        }
        cliente.setEnderecos(enderecos);
        cliente.setCartao(cartaoCredito);
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid ClienteUpdateDTO clienteDTO) {
        if(!clienteService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        
        Cliente cliente = clienteService.findById(id).get();
        if(!clienteDTO.getEmail().equals(cliente.getEmail())){
            if(clienteService.existsByEmail(clienteDTO.getEmail())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já em uso!");
            }
        }

        BeanUtils.copyProperties(clienteDTO, cliente);
        if(clienteDTO.getEnderecos() != null){
            List<Endereco> enderecos = new ArrayList<>();
            for(Endereco endereco : cliente.getEnderecos()){
                endereco = enderecoService.findById(endereco.getId()).get();
                enderecos.add(endereco);
            }
            cliente.setEnderecos(enderecos);
        }
        if(clienteDTO.getCartao() != null){
            cliente.setCartao(cartaoCreditoService.findById(cliente.getCartao().getId()).get());
        }

        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if(!clienteService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        clienteService.deleteById(id);
        return ResponseEntity.ok("Cliente deletado!");
    }
}
