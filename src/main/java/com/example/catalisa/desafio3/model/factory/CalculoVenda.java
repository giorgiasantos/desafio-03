package com.example.catalisa.desafio3.model.factory;

public class CalculoVenda implements Calculo{
    @Override
    public int calcularEstoque(int qtdeEstoque, int qdteCompra) {
        return qtdeEstoque - qdteCompra;
    }
}
