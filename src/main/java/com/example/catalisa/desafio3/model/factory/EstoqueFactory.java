package com.example.catalisa.desafio3.model.factory;

public class EstoqueFactory {

    public Calculo operacao(String operacao){
        if(operacao.equalsIgnoreCase("compra")){
            return new CalculoCompra();
        } else if (operacao.equalsIgnoreCase("venda")) {
            return new CalculoVenda();
        }else{
            return null;
        }
    }

}
