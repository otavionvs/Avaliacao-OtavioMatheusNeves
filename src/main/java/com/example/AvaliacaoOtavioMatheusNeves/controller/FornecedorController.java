package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.FornecedorDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.dto.FornecedorUpdateDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.dto.ProdutoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Fornecedor;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Produto;
import com.example.AvaliacaoOtavioMatheusNeves.service.FornecedorService;
import com.example.AvaliacaoOtavioMatheusNeves.service.ProdutoService;
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
@RequestMapping("/prova/fornecedor")
@AllArgsConstructor
public class FornecedorController {
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;

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
        Produto produto;
        List<Produto> produtos = new ArrayList<>();
        BeanUtils.copyProperties(fornecedorDTO, fornecedor);
        for(ProdutoDTO produtoDTO : fornecedorDTO.getProdutos()){
            produto = new Produto();
            BeanUtils.copyProperties(produtoDTO, produto);
            produtos.add(produto);
        }
        fornecedor.setProdutos(produtos);
        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid FornecedorUpdateDTO fornecedorDTO) {
        if(!fornecedorService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }
        Fornecedor fornecedor = fornecedorService.findById(id).get();
        BeanUtils.copyProperties(fornecedorDTO, fornecedor);
        if(fornecedorDTO.getProdutos() != null){
            List<Produto> produtos = new ArrayList<>();
            for(Produto produto : fornecedor.getProdutos()){
                produto = produtoService.findById(produto.getId()).get();
                produtos.add(produto);
            }
            fornecedor.setProdutos(produtos);
        }
        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if(!fornecedorService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado!");
        }
        fornecedorService.deleteById(id);
        return ResponseEntity.ok("Fornecedor deletado!");
    }
}
