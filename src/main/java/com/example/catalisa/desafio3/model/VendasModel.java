package com.example.catalisa.desafio3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VendasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false)
    private int quantidadeProduto;

    @PastOrPresent
    @Column(length = 10, nullable = false)
    private LocalDate dataVenda;

    @Column(length = 250, nullable = false)
    private String nomeCliente;

    @Email
    private String emailCliente;

    @ManyToOne
    @JoinColumn(name = "produtoId", referencedColumnName = "id", insertable = false, updatable = false)
    private ProdutosModel produto;
    private Long produtoId;

}
