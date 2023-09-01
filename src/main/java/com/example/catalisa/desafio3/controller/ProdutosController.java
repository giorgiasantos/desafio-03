package com.example.catalisa.desafio3.controller;


import com.example.catalisa.desafio3.mapper.ProdutosMapper;
import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import com.example.catalisa.desafio3.model.dto.ProdutosDTOView;
import com.example.catalisa.desafio3.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
@Tag(name = "produtos")
@RestController
@RequestMapping(path = "/api/produtos")

public class ProdutosController {
    @Autowired
    private ProdutosService produtosService;

    //ENDPOINTS GET
    @Operation(summary = "Busca todos os produtos", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "OK"))
    @GetMapping
    public List<ProdutosDTOView> listarProdutos(){
        return produtosService.getAll();
    }

    @Operation(summary = "Busca um produto por id. ", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "OK"))
    @GetMapping(path = "/buscaId/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id){
        Optional<ProdutosDTO> produto = produtosService.getById(id);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. Tente novamente.");
        }

        return ResponseEntity.ok(produto.get());
    }

    @Operation(summary = "Busca um produto pelo nome. ", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "OK"))
    @GetMapping(path = "/buscaNome/{nomeProduto}")
    public ResponseEntity<?> buscarProdutoPorNome(@PathVariable String nomeProduto){
        Optional<ProdutosDTO> produto = produtosService.getByNome(nomeProduto);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. Tente novamente.");
        }
        return ResponseEntity.ok(produto.get());

    }
    //ENDPOINT POST
    @Operation(summary = "Cria um novo produto no sistema. ", method = "POST")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso."))
    @PostMapping
    public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutosDTO produtosDTO) {

        ProdutosModel novoProdutoModel = ProdutosMapper.INSTANCE.produtosDTOToProdutosModel(produtosDTO);
        ProdutosDTO novoProdutoDTO = produtosService.cadastrar(new ProdutosDTO(novoProdutoModel));

        return ResponseEntity.ok("Produto cadastrado com sucesso.");
    }

    //ENDPOINT PUT
    @Operation(summary = "Altera um produto já criado. É possível mudar nome, descrição e valor. ", method = "PUT")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "OK"))
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProdutosModel> alterarProduto(@PathVariable Long id, @RequestBody ProdutosModel produtosModel){

        ProdutosModel produtoAlterado = produtosService.alterar(id,produtosModel);

        if(produtoAlterado != null){
            return ResponseEntity.ok().body(produtoAlterado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    //ENDPOINT DELETE
    @Operation(summary = "Exclui um produto. ", method = "DELETE")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = " "))
    @DeleteMapping(path = "/{id}")
    public void deletarProduto(@PathVariable Long id){
        produtosService.deletar(id);
    }
}
