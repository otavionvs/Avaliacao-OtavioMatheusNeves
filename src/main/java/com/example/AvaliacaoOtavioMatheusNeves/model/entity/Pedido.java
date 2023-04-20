package com.example.AvaliacaoOtavioMatheusNeves.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pedido")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Double valorTotal;

    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> produtos;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_endereco_entrega")
    private EnderecoEntrega endereco;
}
