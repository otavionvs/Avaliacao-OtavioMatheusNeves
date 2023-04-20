package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EnderecoDTO {
    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;
}
