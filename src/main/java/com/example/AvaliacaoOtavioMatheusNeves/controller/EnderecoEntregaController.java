package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.EnderecoEntregaDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.EnderecoEntrega;
import com.example.AvaliacaoOtavioMatheusNeves.service.EnderecoEntregaService;
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
@RequestMapping("/prova/endereco_entrega")
@AllArgsConstructor
public class EnderecoEntregaController {
    private EnderecoEntregaService enderecoEntregaService;

    @GetMapping
    public ResponseEntity<List<EnderecoEntrega>> findAll() {
        return ResponseEntity.ok(enderecoEntregaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<EnderecoEntrega> enderecoEntregaOptional = enderecoEntregaService.findById(id);
        if(enderecoEntregaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EnderecoEntrega não encontrado!");
        }
        return ResponseEntity.ok(enderecoEntregaOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoEntregaDTO enderecoEntregaDTO) {
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        BeanUtils.copyProperties(enderecoEntregaDTO, enderecoEntrega);
        return ResponseEntity.ok(enderecoEntregaService.save(enderecoEntrega));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid EnderecoEntregaDTO enderecoEntregaDTO) {
        if(!enderecoEntregaService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EnderecoEntrega não encontrado!");
        }
        EnderecoEntrega enderecoEntrega = enderecoEntregaService.findById(id).get();
        BeanUtils.copyProperties(enderecoEntregaDTO, enderecoEntrega);
        return ResponseEntity.ok(enderecoEntregaService.save(enderecoEntrega));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id) {
        if(!enderecoEntregaService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EnderecoEntrega não encontrado!");
        }
        enderecoEntregaService.deleteById(id);
        return ResponseEntity.ok("EnderecoEntrega deletado!");
    }
}
