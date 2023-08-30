package com.example.catalisa.desafio3.model.dto;


import com.example.catalisa.desafio3.model.ProdutosModel;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProdutosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeProduto;
    private String descricao;
    private int quantidadeEstoque;
    private double valor;
    private LocalDate dataRegistro;
    private String operacao;

    public ProdutosDTO(ProdutosModel produtosModel) {
        this.id = produtosModel.getId();
        this.nomeProduto = produtosModel.getNomeProduto();
        this.descricao = produtosModel.getDescricao();
        this.quantidadeEstoque = produtosModel.getQuantidadeEstoque();
        this.valor = produtosModel.getValor();
        this.dataRegistro = produtosModel.getDataRegistro();
        this.operacao = produtosModel.getOperacao();
    }

    public ProdutosDTO() {
    }

    public ProdutosModel toProdutosModel(){
        return new ProdutosModel(id,nomeProduto,descricao,quantidadeEstoque, valor,dataRegistro,operacao);
    }
}
