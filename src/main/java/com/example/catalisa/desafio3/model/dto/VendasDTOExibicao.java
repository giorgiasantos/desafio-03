package com.example.catalisa.desafio3.model.dto;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.VendasModel;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class VendasDTOExibicao implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int quantidadeProduto;
    private LocalDate dataVenda;
    private Long produtoId;

    public VendasDTOExibicao(VendasModel vendasModel) {
        this.id = vendasModel.getId();
        this.quantidadeProduto = vendasModel.getQuantidadeProduto();
        this.dataVenda = vendasModel.getDataVenda();
        this.produtoId = vendasModel.getProdutoId();
    }
}
