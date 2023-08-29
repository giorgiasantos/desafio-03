package com.example.catalisa.desafio3.controller;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import com.example.catalisa.desafio3.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/produtos")

public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    //ENDPOINTS GET
    @GetMapping
    public List<ProdutosDTO> listarProdutos(){
        return produtosService.getAll();
    }

    @GetMapping(path = "/buscaId/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id){
        Optional<ProdutosDTO> produto = produtosService.getById(id);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. Tente novamente.");
        }

        return ResponseEntity.ok(produto.get());
    }

    @GetMapping(path = "/buscaNome/{nomeProduto}")
    public ResponseEntity<?> buscarProdutoPorNome(@PathVariable String nomeProduto){
        Optional<ProdutosDTO> produto = produtosService.getByNome(nomeProduto);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. Tente novamente.");
        }
        return ResponseEntity.ok(produto.get());

    }
    //ENDPOINT POST
    @PostMapping
    public ResponseEntity<ProdutosDTO> cadastrarProduto(@RequestBody ProdutosDTO produtosDTO){
        ProdutosDTO novoProduto = produtosService.cadastrar(produtosDTO);
        return ResponseEntity.ok().body(novoProduto);
    }

    //ENDPOINT PUT
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
    @DeleteMapping(path = "/{id}")
    public void deletarProduto(@PathVariable Long id){
        produtosService.deletar(id);
    }
}
