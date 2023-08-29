package com.example.catalisa.desafio3.service;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import com.example.catalisa.desafio3.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    //LISTAR TODOS OS PRODUTOS CADASTRADOS
    public List<ProdutosDTO> getAll(){
        List<ProdutosModel> produtos = produtosRepository.findAll();
        List<ProdutosDTO> produtosDTO = new ArrayList<>();

        for(ProdutosModel produto : produtos){
            produtosDTO.add(new ProdutosDTO(produto));
        }

        return produtosDTO;
    }

    //BUSCAR UM PRODUTO POR ID
    public Optional<ProdutosDTO> getById(Long id){
        Optional<ProdutosModel>  produto = produtosRepository.findById(id);

        if(produto.isPresent()){
            return Optional.of(new ProdutosDTO(produto.get()));
        }
        return Optional.empty();
    }

    //BUSCAR UM PRODUTO PELO NOME
    public Optional<ProdutosDTO> getByNome(String nomeProduto){
        Optional<ProdutosModel> produto = produtosRepository.findByNome(nomeProduto);

        if(produto.isPresent()){
            return Optional.of(new ProdutosDTO(produto.get()));
        }
        return Optional.empty();
    }

    // CADASTRAR UM PRODUTO
    public ProdutosDTO cadastrar(ProdutosDTO produtosDTO){
        ProdutosModel novoProduto = produtosRepository.save(produtosDTO.toProdutosModel());
        return new ProdutosDTO(novoProduto);
    }

    // ATUALIZAR UM PRODUTO JÁ CADASTRADO
    public ProdutosModel alterar(Long id, ProdutosModel produtosModel){

        ProdutosModel produto = produtosRepository.findById(id).get();

        if(produto != null){
            produto.setNomeProduto(produtosModel.getNomeProduto());
        }
        if(produto != null){
            produto.setDataRegistro(produtosModel.getDataRegistro());
        }
        if(produto != null){
            produto.setValor(produtosModel.getValor());
        }
        if(produto != null){
            produto.setQuantidadeEstoque(produtosModel.getQuantidadeEstoque());
        }
        if(produto != null){
            produto.setDescricao(produtosModel.getDescricao());
        }
        return produtosRepository.save(produto);
    }

    //DELETAR PRODUTO
    public void deletar(Long id){
        produtosRepository.deleteById(id);
    }
}
