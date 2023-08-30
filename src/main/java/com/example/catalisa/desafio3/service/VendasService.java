package com.example.catalisa.desafio3.service;

import com.example.catalisa.desafio3.model.VendasModel;
import com.example.catalisa.desafio3.model.dto.VendasDTO;
import com.example.catalisa.desafio3.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository;


    //LISTAR TODAS AS VENDAS
    public List<VendasDTO> getAll (){

        List<VendasModel> vendas = vendasRepository.findAll();
        List<VendasDTO> vendasDTO = new ArrayList<>();

        for(VendasModel venda: vendas){
            vendasDTO.add(new VendasDTO(venda));
        }

        return vendasDTO;
    }

    //BUSCAR UMA VENDA POR ID
    public Optional<VendasDTO> getById(Long id){
        Optional<VendasModel> venda = vendasRepository.findById(id);

        if(venda.isPresent()){
            return Optional.of(new VendasDTO(venda.get()));
        }
        return Optional.empty();
    }

    //CADASTRAR UMA NOVA VENDA
    public VendasDTO cadastrar (VendasDTO vendasDTO){
        VendasModel novaVenda = vendasRepository.save(vendasDTO.toVendasModel());

        return new VendasDTO(novaVenda);
    }

    //ALTERAR UMA VENDA
    public VendasModel alterar(Long id, VendasModel vendasModel){
        VendasModel venda = vendasRepository.findById(id).get();

        if(venda != null){
            venda.setQuantidadeProduto(vendasModel.getQuantidadeProduto());
        }
        if(venda != null){
            venda.setDataVenda(vendasModel.getDataVenda());
        }
        if(venda != null){
            venda.setNomeCliente(vendasModel.getNomeCliente());
        }
        if(venda != null){
            venda.setEmailCliente(vendasModel.getEmailCliente());
        }

        return vendasRepository.save(venda);
    }

    //DELETAR VENDA
    public void deletar (Long id){
        vendasRepository.deleteById(id);
    }
}
