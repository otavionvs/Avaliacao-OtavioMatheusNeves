package com.example.AvaliacaoOtavioMatheusNeves.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProdutoPedido> produtos;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco_entrega")
    private EnderecoEntrega endereco;
}
