package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.ProdutoPedidoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.ProdutoPedido;
import com.example.AvaliacaoOtavioMatheusNeves.service.ProdutoPedidoService;
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
@RequestMapping("/prova/produto_pedido")
@AllArgsConstructor
public class ProdutoPedidoController {
    private ProdutoPedidoService produtoPedidoService;

    @GetMapping
    public ResponseEntity<List<ProdutoPedido>> findAll() {
        return ResponseEntity.ok(produtoPedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<ProdutoPedido> produtoPedidoOptional = produtoPedidoService.findById(id);
        if(produtoPedidoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido não encontrado!");
        }
        return ResponseEntity.ok(produtoPedidoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO) {
        ProdutoPedido produtoPedido = new ProdutoPedido();
        BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
        return ResponseEntity.ok(produtoPedidoService.save(produtoPedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProdutoPedidoDTO produtoPedidoDTO) {
        if(!produtoPedidoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido não encontrado!");
        }
        ProdutoPedido produtoPedido = produtoPedidoService.findById(id).get();
        BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
        return ResponseEntity.ok(produtoPedidoService.save(produtoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(Long id) {
        if(!produtoPedidoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProdutoPedido não encontrado!");
        }
        produtoPedidoService.deleteById(id);
        return ResponseEntity.ok("ProdutoPedido deletado!");
    }

}
