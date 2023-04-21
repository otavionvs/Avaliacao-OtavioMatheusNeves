package com.example.AvaliacaoOtavioMatheusNeves.controller;

import com.example.AvaliacaoOtavioMatheusNeves.model.dto.PedidoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.dto.PedidoUpdateDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.dto.ProdutoPedidoDTO;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.EnderecoEntrega;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Pedido;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.ProdutoPedido;
import com.example.AvaliacaoOtavioMatheusNeves.service.EnderecoEntregaService;
import com.example.AvaliacaoOtavioMatheusNeves.service.PedidoService;
import com.example.AvaliacaoOtavioMatheusNeves.service.ProdutoPedidoService;
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
@RequestMapping("/prova/pedido")
@AllArgsConstructor
public class PedidoController {
    private PedidoService pedidoService;
    private ProdutoPedidoService produtoPedidoService;
    private EnderecoEntregaService enderecoEntregaService;

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
        ProdutoPedido produtoPedido;
        List<ProdutoPedido> produtos = new ArrayList<>();
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        BeanUtils.copyProperties(pedidoDTO, pedido);
        for(ProdutoPedidoDTO produtoPedidoDTO :  pedidoDTO.getProdutos()){
            produtoPedido = new ProdutoPedido();
            BeanUtils.copyProperties(produtoPedidoDTO, produtoPedido);
            System.out.println(produtoPedido.getQuantidade());
            produtoPedido.setProduto(produtoPedidoDTO.getProduto());
            produtos.add(produtoPedido);
        }
        pedido.setProdutos(produtos);
        BeanUtils.copyProperties(pedidoDTO.getEndereco(), enderecoEntrega);
        pedido.setEndereco(enderecoEntrega);
        pedido = pedidoService.save(pedido);
        for(ProdutoPedido produto : pedido.getProdutos()){
            produto.setPedido(pedido);
            produtoPedidoService.save(produto);
        }
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid PedidoUpdateDTO pedidoDTO) {
        if(!pedidoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        Pedido pedido = pedidoService.findById(id).get();
        BeanUtils.copyProperties(pedidoDTO, pedido);
        if(pedidoDTO.getProdutos() != null){
            List<ProdutoPedido> produtoPedidos = new ArrayList<>();
            for(ProdutoPedido produtoPedido : pedido.getProdutos()){
                produtoPedido = produtoPedidoService.findById(produtoPedido.getId()).get();
                produtoPedidos.add(produtoPedido);
            }
            pedido.setProdutos(produtoPedidos);
        }
        if(pedidoDTO.getEndereco() != null){
            pedido.setEndereco(enderecoEntregaService.findById(pedido.getEndereco().getId()).get());
        }

        return ResponseEntity.ok(pedidoService.save(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        if(!pedidoService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
        }
        pedidoService.deleteById(id);
        return ResponseEntity.ok("Pedido deletado!");
    }
}
