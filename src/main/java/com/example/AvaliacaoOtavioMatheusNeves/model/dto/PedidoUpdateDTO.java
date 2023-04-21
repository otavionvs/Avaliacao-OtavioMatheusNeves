package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Cliente;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.EnderecoEntrega;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.ProdutoPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoUpdateDTO {
    @NotNull
    @Positive
    private Double valorTotal;

    private List<ProdutoPedido> produtos;

    private Cliente cliente;

    private EnderecoEntrega endereco;
}
