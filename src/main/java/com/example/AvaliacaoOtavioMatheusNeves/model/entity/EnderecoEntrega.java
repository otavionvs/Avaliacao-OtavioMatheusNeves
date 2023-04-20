package com.example.AvaliacaoOtavioMatheusNeves.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "endereco_entrega")
@Data
public class EnderecoEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne(mappedBy = "endereco")
    private Pedido pedido;

}
