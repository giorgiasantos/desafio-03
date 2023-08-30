package com.example.catalisa.desafio3.model.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoVendaTest {

    CalculoVenda calculoVenda = new CalculoVenda();

    @Test
    void testeCalcularEstoqueVenda() {
        int qtdeEstoque = 10;
        int qtdePedido = 1;

        int resultado = calculoVenda.calcularEstoque(qtdeEstoque,qtdePedido);

        int resultadoEsperado = 9;

        assertEquals(resultado,resultadoEsperado);
    }
}