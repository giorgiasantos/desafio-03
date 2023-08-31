package com.example.catalisa.desafio3.model.dto;

import com.example.catalisa.desafio3.model.ProdutosModel;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Data

public class ProdutosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeProduto;
    private String descricao;
    private String tipoPedido;
    private int qtdProduto;
    private double valor;
    private LocalDate dataRegistro;
    private String nomeResponsavel;
    private String emailResponsavel;

    public ProdutosDTO(ProdutosModel produtosModel) {
        this.id = produtosModel.getId();
        this.nomeProduto = produtosModel.getNomeProduto();
        this.descricao = produtosModel.getDescricao();
        this.tipoPedido = produtosModel.getTipoPedido();
        this.qtdProduto = produtosModel.getQtdProduto();
        this.valor = produtosModel.getValor();
        this.dataRegistro = produtosModel.getDataRegistro();
        this.nomeResponsavel = produtosModel.getNomeResponsavel();
        this.emailResponsavel = produtosModel.getEmailResponsavel();
    }

    public ProdutosDTO() {
    }

    public ProdutosModel toProdutosModel(){
        return new ProdutosModel(id,nomeProduto,descricao,tipoPedido,qtdProduto,valor,dataRegistro,nomeResponsavel,emailResponsavel);
    }
}
