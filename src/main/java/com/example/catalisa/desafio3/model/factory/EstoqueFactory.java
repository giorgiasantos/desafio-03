package com.example.catalisa.desafio3.model.factory;

public class EstoqueFactory {

    public Calculo operacao(String tipoPedido){
        if(tipoPedido.equalsIgnoreCase("compra")){
            return new CalculoCompra();
        } else if (tipoPedido.equalsIgnoreCase("venda")) {
            return new CalculoVenda();
        }else{
            return null;
        }
    }

}
