package com.example.catalisa.desafio3.repository;

import com.example.catalisa.desafio3.model.ProdutosModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProdutosRepositoryTest {

    @Autowired
    private ProdutosRepository produtosRepository;

    @AfterEach
    void tearDown() {
        produtosRepository.deleteAll();;
    }

    @Test
    void testeFindByNome() {

        ProdutosModel produtosModel = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",200, 0, 300, LocalDate.parse("2023-06-06"));

        produtosRepository.save(produtosModel);

        Optional<ProdutosModel> resultado = produtosRepository.findByNome("Jaqueta Jeans");

        assertNotNull(resultado);

    }

    @Test
    void testeFindByNameNaoEncontraPorNome(){

        String nomeProduto = "Tênis Converse All Star";
        Optional<ProdutosModel> resultado = produtosRepository.findByNome(nomeProduto);

        assertNull(resultado.orElse(null));
    }
}