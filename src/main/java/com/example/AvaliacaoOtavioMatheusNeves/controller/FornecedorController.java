package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.FornecedorDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Fornecedor;
import com.example.AvaliacaoOtavioMatheusNeves.service.FornecedorService;
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
@RequestMapping("/prova/fornecedor")
@AllArgsConstructor
public class FornecedorController {
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);
        if(fornecedorOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }
        return ResponseEntity.ok(fornecedorOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        BeanUtils.copyProperties(fornecedorDTO, fornecedor);
        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid FornecedorDTO fornecedorDTO) {
        if(!fornecedorService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }
        Fornecedor fornecedor = fornecedorService.findById(id).get();
        BeanUtils.copyProperties(fornecedorDTO, fornecedor);
        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id) {
        if(!fornecedorService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }
        fornecedorService.deleteById(id);
        return ResponseEntity.ok("Fornecedor deletado!");
    }
}
