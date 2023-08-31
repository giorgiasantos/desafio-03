package com.example.catalisa.desafio3.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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

    @Column(length = 6, nullable = false)
    private String tipoPedido;

    @PositiveOrZero
    @Column(nullable = false)
    private int qtdProduto;

    @Positive
    @Column(length = 6, nullable = false)
    private double valor;

    @PastOrPresent
    @Column(length = 10, nullable = false)
    private LocalDate dataRegistro;

    @Column(length = 200, nullable = false)
    private String nomeResponsavel;

    @Email
    private String emailResponsavel;

    public ProdutosModel(ProdutosModel produtosModel) {
    }


}
