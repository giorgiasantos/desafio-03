package com.example.catalisa.desafio3.service;


import com.example.catalisa.desafio3.mapper.ProdutosMapper;
import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import com.example.catalisa.desafio3.model.dto.ProdutosDTOView;
import com.example.catalisa.desafio3.repository.ProdutosRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    //LISTAR TODOS OS PRODUTOS CADASTRADOS
    public List<ProdutosDTOView> getAll(){
        List<ProdutosModel> produtos = produtosRepository.findAll();
        List<ProdutosDTOView> produtosDTO = new ArrayList<>();

        for(ProdutosModel produto : produtos){
            produtosDTO.add(new ProdutosDTOView(produto));
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

        ProdutosModel novoProduto = ProdutosMapper.INSTANCE.produtosDTOToProdutosModel(produtosDTO);
        produtosRepository.save(novoProduto);

        return new ProdutosDTO(novoProduto);
    }

    // ATUALIZAR UM PRODUTO JÁ CADASTRADO
    public ProdutosModel alterar(Long id, ProdutosModel produtosModel){

        ProdutosModel produto = produtosRepository.findById(id).get();

        if(produto != null){
            produto.setNomeProduto(produtosModel.getNomeProduto());
        }
        if(produto != null){
            produto.setValor(produtosModel.getValor());
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
