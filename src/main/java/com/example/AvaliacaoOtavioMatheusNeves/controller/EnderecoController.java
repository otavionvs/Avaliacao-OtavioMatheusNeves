package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.EnderecoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import com.example.AvaliacaoOtavioMatheusNeves.service.EnderecoService;
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
@RequestMapping("/prova/endereco")
@AllArgsConstructor
public class EnderecoController {
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);
        if(enderecoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }
        return ResponseEntity.ok(enderecoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return ResponseEntity.ok(enderecoService.save(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid EnderecoDTO enderecoDTO) {
        if(!enderecoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }
        Endereco endereco = enderecoService.findById(id).get();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return ResponseEntity.ok(enderecoService.save(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id) {
        if(!enderecoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }
        enderecoService.deleteById(id);
        return ResponseEntity.ok("Endereco deletado!");
    }
}
