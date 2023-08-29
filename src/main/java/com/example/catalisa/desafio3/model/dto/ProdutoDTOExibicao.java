package com.example.catalisa.desafio3.model.dto;

import com.example.catalisa.desafio3.model.ProdutosModel;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ProdutoDTOExibicao implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeProduto;
    private double valor;

    public ProdutoDTOExibicao(ProdutosModel produtosModel) {
        this.id = produtosModel.getId();
        this.nomeProduto = produtosModel.getNomeProduto();
        this.valor = produtosModel.getValor();
    }
}
