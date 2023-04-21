package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.CartaoCreditoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.CartaoCredito;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import com.example.AvaliacaoOtavioMatheusNeves.service.CartaoCreditoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/prova/cartaocredito")
@AllArgsConstructor
public class CartaoCreditoController {
    private CartaoCreditoService cartaoCreditoService;

    @GetMapping
    public ResponseEntity<List<CartaoCredito>> findAll() {
        return ResponseEntity.ok(cartaoCreditoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<CartaoCredito> cartaoCreditoOptional = cartaoCreditoService.findById(id);
        if(cartaoCreditoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão de Credito não encontrado!");
        }
        return ResponseEntity.ok(cartaoCreditoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CartaoCreditoDTO cartaoCreditoDTO) {
        CartaoCredito cartaoCredito = new CartaoCredito();
        BeanUtils.copyProperties(cartaoCreditoDTO, cartaoCredito);
        return ResponseEntity.ok(cartaoCreditoService.save(cartaoCredito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid CartaoCreditoDTO cartaoCreditoDTO) {
        if(!cartaoCreditoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão de Credito não encontrado!");
        }
        CartaoCredito cartaoCredito = cartaoCreditoService.findById(id).get();
        BeanUtils.copyProperties(cartaoCreditoDTO, cartaoCredito);
        return ResponseEntity.ok(cartaoCreditoService.save(cartaoCredito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if(!cartaoCreditoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão de Credito não encontrado!");
        }
        cartaoCreditoService.deleteById(id);
        return ResponseEntity.ok("Cartão de Credito deletado!");
    }
}
