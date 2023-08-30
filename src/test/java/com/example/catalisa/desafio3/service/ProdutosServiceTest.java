package com.example.catalisa.desafio3.service;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import com.example.catalisa.desafio3.model.dto.ProdutosDTOView;
import com.example.catalisa.desafio3.model.factory.Calculo;
import com.example.catalisa.desafio3.model.factory.CalculoCompra;
import com.example.catalisa.desafio3.model.factory.CalculoVenda;
import com.example.catalisa.desafio3.model.factory.EstoqueFactory;
import com.example.catalisa.desafio3.repository.ProdutosRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ProdutosServiceTest {

    @InjectMocks
    private ProdutosService produtosService;
    @Mock
    private ProdutosRepository produtosRepository;

    @Test
    void testGetAll() throws Exception{
        List<ProdutosModel> listaMock = new ArrayList<>();
        listaMock.add(new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",200, 0, 300, LocalDate.parse("2023-06-06")));

        when(produtosRepository.findAll()).thenReturn(listaMock);

        List<ProdutosDTOView> listaResultado = produtosService.getAll();
        verify(produtosRepository).findAll();

        assertNotNull(listaResultado);
        assertEquals(listaMock.size(),listaResultado.size());
        assertEquals(listaMock.get(0).getNomeProduto(), listaResultado.get(0).getNomeProduto());
        assertEquals(listaMock.get(0).getTipoPedido(), listaResultado.get(0).getTipoPedido());
        assertEquals(listaMock.get(0).getValor(), listaResultado.get(0).getValor());
        assertEquals(listaMock.get(0).getDataRegistro(), listaResultado.get(0).getDataRegistro());
    }

    @Test
    void testeGetByIdAchaId() throws Exception{
        ProdutosModel produto = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",200, 0, 300, LocalDate.parse("2023-06-06"));

        when(produtosRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<ProdutosDTO> resultado = produtosService.getById(1L);
        verify(produtosRepository,times(1)).findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(produto.getId(),resultado.get().getId());
        assertEquals(produto.getNomeProduto(),resultado.get().getNomeProduto());
        assertEquals(produto.getDescricao(), resultado.get().getDescricao());
        assertEquals(produto.getTipoPedido(), resultado.get().getTipoPedido());
        assertEquals(produto.getQuantidadeOperacao(),resultado.get().getQuantidadeOperacao());
        assertEquals(produto.getQuantidadeEstoque(), resultado.get().getQuantidadeEstoque());
        assertEquals(produto.getValor(), resultado.get().getValor());
        assertEquals(produto.getDataRegistro(), resultado.get().getDataRegistro());
    }

    @Test
    void testeGetByIdNaoAchaId() throws Exception{
        Long id = 2L;

        when(produtosRepository.findById(id)).thenReturn(Optional.empty());
        Optional<ProdutosDTO> resultado = produtosService.getById(id);

        verify(produtosRepository, times(1)).findById(id);
        assertFalse(resultado.isPresent());

    }

    @Test
    void testGetByNome() throws Exception{
        ProdutosModel produto = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",200, 0, 300, LocalDate.parse("2023-06-06"));

        when(produtosRepository.findByNome("Jaqueta Jeans")).thenReturn(Optional.of(produto));

        Optional<ProdutosDTO> resultado = produtosService.getByNome("Jaqueta Jeans");
        verify(produtosRepository,times(1)).findByNome("Jaqueta Jeans");

        assertTrue(resultado.isPresent());
        assertEquals(produto.getId(),resultado.get().getId());
        assertEquals(produto.getNomeProduto(),resultado.get().getNomeProduto());
        assertEquals(produto.getDescricao(), resultado.get().getDescricao());
        assertEquals(produto.getTipoPedido(), resultado.get().getTipoPedido());
        assertEquals(produto.getQuantidadeOperacao(),resultado.get().getQuantidadeOperacao());
        assertEquals(produto.getQuantidadeEstoque(), resultado.get().getQuantidadeEstoque());
        assertEquals(produto.getValor(), resultado.get().getValor());
        assertEquals(produto.getDataRegistro(), resultado.get().getDataRegistro());

    }

    @Test
    void testGetByNameNaoAchaNome() throws Exception{
        String nomeProduto = "Jaqueta Jeans";

        when(produtosRepository.findByNome(nomeProduto)).thenReturn(Optional.empty());
        Optional<ProdutosDTO> resultado = produtosService.getByNome(nomeProduto);

        verify(produtosRepository, times(1)).findByNome(nomeProduto);
        assertFalse(resultado.isPresent());
    }

    @Test
    @Disabled
    void testCadastrar() throws Exception{
        //mocks
        EstoqueFactory estoqueFactory = mock(EstoqueFactory.class);
        CalculoCompra calculoCompra = mock(CalculoCompra.class);

        when(estoqueFactory.operacao("compra")).thenReturn(calculoCompra);
        when(calculoCompra.calcularEstoque(200,100)).thenReturn(300);

        ProdutosDTO produtosDTO = new ProdutosDTO(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",200, 100, 300.0, LocalDate.parse("2023-06-06"));

        ProdutosDTO resultado = produtosService.cadastrar(produtosDTO,estoqueFactory);

        verify(produtosRepository, times(1)).save(any(ProdutosModel.class));
        verify(estoqueFactory,times(1)).operacao("compra");
        verify(calculoCompra, times(1)).calcularEstoque(anyInt(), anyInt());

        assertNotNull(resultado);
    }

    @Test
    @Disabled
    void testeAlterar() throws Exception{
        EstoqueFactory estoqueFactory = mock(EstoqueFactory.class);

        ProdutosModel produtoAntigo = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",200, 0, 300, LocalDate.parse("2023-06-06"));

        ProdutosModel produtoAlterado = new ProdutosModel(null,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",1, 201, 300, LocalDate.parse("2023-06-06"));

        when(produtosRepository.findById(1L)).thenReturn(Optional.of(produtoAntigo));
        when(produtosRepository.save(any(ProdutosModel.class))).thenReturn(produtoAlterado);

        ProdutosModel resultado = produtosService.alterar(1L, produtoAlterado,estoqueFactory);

        verify(produtosRepository,times(1)).findById(1L);
        verify(produtosRepository, times(1)).save(any(ProdutosModel.class));

        assertEquals(produtoAlterado.getNomeProduto(), resultado.getNomeProduto());

    }

    @Test
    void testeDeletar() throws Exception{
        Long id = 1L;
        produtosService.deletar(id);
        verify(produtosRepository,times(1)).deleteById(id);
    }
}