package com.example.AvaliacaoOtavioMatheusNeves.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "fornecedor")
@Data
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cnpj;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @ManyToMany
    private List<Cliente> clientes;
}
