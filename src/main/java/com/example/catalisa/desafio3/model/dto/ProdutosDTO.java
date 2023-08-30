package com.example.catalisa.desafio3.model.dto;


import com.example.catalisa.desafio3.model.ProdutosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class ProdutosDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeProduto;
    private String descricao;
    private String tipoPedido;
    private int quantidadeOperacao;
    private int quantidadeEstoque;
    private double valor;
    private LocalDate dataRegistro;



    public ProdutosDTO(ProdutosModel produtosModel) {
        this.id = produtosModel.getId();
        this.nomeProduto = produtosModel.getNomeProduto();
        this.descricao = produtosModel.getDescricao();
        this.tipoPedido = produtosModel.getTipoPedido();
        this.quantidadeOperacao = produtosModel.getQuantidadeOperacao();
        this.quantidadeEstoque = produtosModel.getQuantidadeEstoque();
        this.valor = produtosModel.getValor();
        this.dataRegistro = produtosModel.getDataRegistro();

    }

    public ProdutosDTO() {
    }

    public ProdutosModel toProdutosModel(){
        return new ProdutosModel(id,nomeProduto,descricao,tipoPedido, quantidadeOperacao,quantidadeEstoque, valor,dataRegistro);
    }
}
