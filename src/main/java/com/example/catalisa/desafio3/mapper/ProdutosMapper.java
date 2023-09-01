package com.example.catalisa.desafio3.mapper;

import com.example.catalisa.desafio3.model.ProdutosModel;
import com.example.catalisa.desafio3.model.dto.ProdutosDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutosMapper {
    ProdutosMapper INSTANCE = Mappers.getMapper(ProdutosMapper.class);
    @Mapping(target = "id", ignore = false)
    ProdutosDTO ProdutosModelToProdutoDTO(ProdutosModel produtosModel);
    @Mapping(target = "id", ignore = false)
    ProdutosModel produtosDTOToProdutosModel(ProdutosDTO produtosDTO);

}
