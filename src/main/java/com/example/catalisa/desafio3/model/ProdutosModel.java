package com.example.catalisa.desafio3.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProdutosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nomeProduto;

    @Column(length = 200, nullable = false)
    private String descricao;

    @Positive
    @Column(length = 10, nullable = false)
    private int quantidadeEstoque;

    @Positive
    @Column(length = 6, nullable = false)
    private double valor;

    @PastOrPresent
    @Column(length = 10, nullable = false)
    private LocalDate dataRegistro;

    @Column(length = 6, nullable = false)
    private String operacao;

}
