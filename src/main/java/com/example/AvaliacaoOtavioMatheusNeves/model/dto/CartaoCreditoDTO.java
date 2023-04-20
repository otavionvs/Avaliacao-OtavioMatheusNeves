package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartaoCreditoDTO {
    @NotNull
    private String numero;
}
