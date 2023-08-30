package com.example.catalisa.desafio3.model.factory;

public class CalculoCompra implements Calculo{

    @Override
    public int calcularEstoque(int qtdeEstoque, int qdtePedido) {
        return qtdeEstoque + qdtePedido;
    }
}
