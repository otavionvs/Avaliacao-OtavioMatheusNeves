package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.CartaoCredito;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ClienteUpdateDTO {
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    private List<Endereco> enderecos;

    private CartaoCredito cartao;
}
