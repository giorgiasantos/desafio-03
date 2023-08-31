package com.example.catalisa.desafio3.controller;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import com.example.catalisa.desafio3.model.dto.ProdutosDTOView;
import com.example.catalisa.desafio3.service.ProdutosService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProdutosController.class)

class ProdutosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutosService produtosService;

    @Test
    void testeListarProdutos() throws Exception{

        ProdutosModel produtosModel = new ProdutosModel();
        produtosModel.setNomeProduto("Jaqueta Jeans");
        produtosModel.setTipoPedido("compra");
        produtosModel.setValor(300);
        produtosModel.setDataRegistro(LocalDate.parse("2023-06-06"));

        List<ProdutosDTOView> listaProdutos = new ArrayList<>();
        listaProdutos.add(new ProdutosDTOView(produtosModel));

        when(produtosService.getAll()).thenReturn(listaProdutos);

        mockMvc.perform(get("/api/produtos")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nomeProduto").value("Jaqueta Jeans"))
                .andExpect(jsonPath("$[0].tipoPedido").value("compra"))
                .andExpect(jsonPath("$[0].valor").value(300))
                .andExpect(jsonPath("$[0].dataRegistro").value("2023-06-06"));


    }

    @Test
    void testeBuscarProdutoPorId() throws Exception{
        ProdutosModel produtosModel = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",2, 300, LocalDate.parse("2023-06-06"), "João da Silva","joao@gmail.com");

        when(produtosService.getById(produtosModel.getId())).thenReturn(Optional.of(new ProdutosDTO(produtosModel)));

        mockMvc.perform(get("/api/produtos/buscaId/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomeProduto").value("Jaqueta Jeans"))
                .andExpect(jsonPath("$.descricao").value("Jaqueta jeans oversized com bolsos laterais"))
                .andExpect(jsonPath("$.tipoPedido").value("compra"))
                .andExpect(jsonPath("$.qtdProduto").value(2))
                .andExpect(jsonPath("$.valor").value(300))
                .andExpect(jsonPath("$.dataRegistro").value("2023-06-06"))
                .andExpect(jsonPath("$.nomeResponsavel").value("João da Silva"))
                .andExpect(jsonPath("$.emailResponsavel").value("joao@gmail.com"));
    }

    @Test
    void testeBuscarProdutoPorNome() throws Exception{
        ProdutosModel produtosModel = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",2, 300, LocalDate.parse("2023-06-06"), "João da Silva","joao@gmail.com");

        when(produtosService.getByNome(produtosModel.getNomeProduto())).thenReturn(Optional.of(new ProdutosDTO(produtosModel)));

        mockMvc.perform(get("/api/produtos/buscaNome/{nomeProduto}", "Jaqueta Jeans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomeProduto").value("Jaqueta Jeans"))
                .andExpect(jsonPath("$.descricao").value("Jaqueta jeans oversized com bolsos laterais"))
                .andExpect(jsonPath("$.tipoPedido").value("compra"))
                .andExpect(jsonPath("$.qtdProduto").value(2))
                .andExpect(jsonPath("$.valor").value(300))
                .andExpect(jsonPath("$.dataRegistro").value("2023-06-06"))
                .andExpect(jsonPath("$.nomeResponsavel").value("João da Silva"))
                .andExpect(jsonPath("$.emailResponsavel").value("joao@gmail.com"));
    }

    @Test
    void testeCadastrarProduto() throws Exception{

        ProdutosModel produtosModel = new ProdutosModel(1L,"Jaqueta Jeans", "Jaquela jeans em modelagem oversized com bolsos laterais","Compra",10, 80.0, LocalDate.parse("2023-06-06"), "Alfajor","alfajor@gmail.com");

        String jsonContent = objectMapper.writeValueAsString(produtosModel);

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto cadastrado com sucesso."));
    }

//    @Test
//    @Disabled
//    void testeCadastrarProduto() throws Exception{
//
//        ProdutosModel produtosModel = new ProdutosModel(1L,"Jaqueta Jeans", "Jaquela jeans em modelagem oversized com bolsos laterais","Compra",10, 80.0, LocalDate.parse("2023-06-06"), "Alfajor","alfajor@gmail.com");
//
//        String jsonContent = objectMapper.writeValueAsString(produtosModel);
//
//        mockMvc.perform(post("/api/produtos")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonContent))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.nomeProduto").value("Jaqueta Jeans"))
//                .andExpect(jsonPath("$.descricao").value("Jaquela jeans em modelagem oversized com bolsos laterais"))
//                .andExpect(jsonPath("$.tipoPedido").value("Compra"))
//                .andExpect(jsonPath("$.qtdProduto").value(10))
//                .andExpect(jsonPath("$.valor").value(80.0))
//                .andExpect(jsonPath("$.dataRegistro").value("2023-06-06"))
//                .andExpect(jsonPath("$.nomeResponsavel").value("Alfajor"))
//                .andExpect(jsonPath("$.emailResponsavel").value("alfajor@gmail.com"));
//    }

    @Test
    void testeAlterarProduto() throws Exception{
        ProdutosModel produtosModel = new ProdutosModel(1L,"Jaqueta Jeans", "Jaqueta jeans oversized com bolsos laterais","compra",2, 300, LocalDate.parse("2023-06-06"), "João da Silva","joao@gmail.com");

        String jsonContent = objectMapper.writeValueAsString(produtosModel);

        when(produtosService.alterar(eq(1L),any(ProdutosModel.class))).thenReturn(produtosModel);

        mockMvc.perform(put("/api/produtos/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomeProduto").value("Jaqueta Jeans"))
                .andExpect(jsonPath("$.descricao").value("Jaqueta jeans oversized com bolsos laterais"))
                .andExpect(jsonPath("$.tipoPedido").value("compra"))
                .andExpect(jsonPath("$.qtdProduto").value(2))
                .andExpect(jsonPath("$.valor").value(300))
                .andExpect(jsonPath("$.dataRegistro").value("2023-06-06"))
                .andExpect(jsonPath("$.nomeResponsavel").value("João da Silva"))
                .andExpect(jsonPath("$.emailResponsavel").value("joao@gmail.com"));

        verify(produtosService,times(1)).alterar(eq(1L),any(ProdutosModel.class));

    }

    @Test
    void testeDeletarProduto() throws Exception{
        Long id = 1L;
        mockMvc.perform(delete("/api/produtos/{id}",id))
                .andExpect(status().isOk());
    }
}