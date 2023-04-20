package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Cliente;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.EnderecoEntrega;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.ProdutoPedido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoDTO {
    @NotNull
    @Positive
    private Double valorTotal;

    @NotEmpty
    private List<ProdutoPedido> produtos;

    @NotNull
    private Cliente cliente;

    @NotNull
    private EnderecoEntrega endereco;
}
