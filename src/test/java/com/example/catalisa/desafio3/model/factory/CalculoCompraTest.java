package com.example.catalisa.desafio3.model.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CalculoCompraTest {

    CalculoCompra calculoCompra = new CalculoCompra();

    @Test
    void testeCalcularEstoqueCompra() {
        int qtdeEstoque = 0;
        int qtdePedido = 10;

        int resultado = calculoCompra.calcularEstoque(qtdeEstoque,qtdePedido);

        int resultadoEsperado = 10;

        assertEquals(resultado,resultadoEsperado);
    }
}