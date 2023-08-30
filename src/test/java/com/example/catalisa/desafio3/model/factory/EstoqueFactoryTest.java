package com.example.catalisa.desafio3.model.factory;

import com.example.catalisa.desafio3.model.ProdutosModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueFactoryTest {

    EstoqueFactory estoqueFactory = new EstoqueFactory();

    @Test
    void testeOperacaoEstoqueCompra() {

        Calculo calculo = estoqueFactory.operacao("compra");
        assertTrue(calculo instanceof CalculoCompra);

    }

    @Test
    void testeOperacaoEstoqueVenda() {
        Calculo calculo = estoqueFactory.operacao("venda");
        assertTrue(calculo instanceof CalculoVenda);

    }

    @Test
    void testeOperacaoEstoqueNulo() {
        Calculo calculo = estoqueFactory.operacao("Teste");
        assertNull(calculo);
    }
}