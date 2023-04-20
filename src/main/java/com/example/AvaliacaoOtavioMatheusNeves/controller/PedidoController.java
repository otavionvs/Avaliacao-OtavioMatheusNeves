package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.PedidoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Pedido;
import com.example.AvaliacaoOtavioMatheusNeves.service.PedidoService;
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
@RequestMapping("/prova/pedido")
@AllArgsConstructor
public class PedidoController {
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<Pedido> pedidoOptional = pedidoService.findById(id);
        if(pedidoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        return ResponseEntity.ok(pedidoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDTO, pedido);
        return ResponseEntity.ok(pedidoService.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid PedidoDTO pedidoDTO) {
        if(!pedidoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        Pedido pedido = pedidoService.findById(id).get();
        BeanUtils.copyProperties(pedidoDTO, pedido);
        return ResponseEntity.ok(pedidoService.save(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id) {
        if(!pedidoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        pedidoService.deleteById(id);
        return ResponseEntity.ok("Pedido deletado!");
    }
}
