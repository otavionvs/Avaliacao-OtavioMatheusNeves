package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;

@Getter
public class ProdutoDTO {
    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private Double preco;

    @NotNull
    @Positive
    private Integer quantidade;

}
