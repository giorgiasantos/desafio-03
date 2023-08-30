package com.example.catalisa.desafio3.model.dto;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.VendasModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class VendasDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private int quantidadeProduto;
    private LocalDate dataVenda;
    private String nomeCliente;
    private String emailCliente;
    private String operacao;
    private ProdutosModel produto;
    private Long produtoId;

    public VendasDTO(VendasModel vendasModel) {
        this.id = vendasModel.getId();
        this.quantidadeProduto = vendasModel.getQuantidadeProduto();
        this.dataVenda = vendasModel.getDataVenda();
        this.nomeCliente = vendasModel.getNomeCliente();
        this.emailCliente = vendasModel.getEmailCliente();
        this.operacao = vendasModel.getOperacao();
        this.produto = vendasModel.getProduto();
        this.produtoId = vendasModel.getProdutoId();
    }

    public VendasDTO() {
    }

    public VendasModel toVendasModel(){
        return new VendasModel(id, quantidadeProduto, dataVenda,nomeCliente,emailCliente,operacao,produto, produtoId);
    }
}
