package com.example.catalisa.desafio3.repository;

import com.example.catalisa.desafio3.model.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosModel,Long> {

    @Query("SELECT nome FROM ProdutosModel nome WHERE LOWER (nome.nomeProduto) = LOWER(:nomeProduto)")
    Optional<ProdutosModel> findByNome(String nomeProduto);

}
