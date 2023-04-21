package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Pedido;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProdutoPedidoDTO {
    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private Produto produto;
}
