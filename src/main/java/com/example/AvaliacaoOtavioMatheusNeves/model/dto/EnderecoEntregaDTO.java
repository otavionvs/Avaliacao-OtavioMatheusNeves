package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Pedido;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EnderecoEntregaDTO {
    @NotNull
    private Endereco endereco;
    @NotNull
    private Pedido pedido;
}
