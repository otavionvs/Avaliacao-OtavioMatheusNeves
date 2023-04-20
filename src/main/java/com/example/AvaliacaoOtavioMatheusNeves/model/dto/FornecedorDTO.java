package com.example.AvaliacaoOtavioMatheusNeves.model.dto;

import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Cliente;
import com.example.AvaliacaoOtavioMatheusNeves.model.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public class FornecedorDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String cnpj;

    @NotEmpty
    private List<Produto> produtos;

    @NotEmpty
    private List<Cliente> clientes;
}
