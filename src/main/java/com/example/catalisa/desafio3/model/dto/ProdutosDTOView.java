package com.example.catalisa.desafio3.model.dto;

import com.example.catalisa.desafio3.model.ProdutosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ProdutosDTOView {
    private static final long serialVersionUID = 1L;

    private String nomeProduto;
    private String tipoPedido;
    private double valor;
    private LocalDate dataRegistro;

    public ProdutosDTOView(ProdutosModel produtosModel) {
        this.nomeProduto = produtosModel.getNomeProduto();
        this.tipoPedido = produtosModel.getTipoPedido();
        this.valor = produtosModel.getValor();
        this.dataRegistro = produtosModel.getDataRegistro();
    }

    public ProdutosDTOView() {
    }
}
