package com.example.catalisa.desafio3.controller;
import com.example.catalisa.desafio3.model.VendasModel;
import com.example.catalisa.desafio3.model.dto.VendasDTO;
import com.example.catalisa.desafio3.service.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/vendas")

public class VendasController {
    @Autowired
    private VendasService vendasService;


    //ENDPOINTS GET
    @GetMapping
    public List<VendasDTO> listarVendas(){
        return vendasService.getAll();
    }

    @GetMapping(path = "/buscaId/{id}")
    public ResponseEntity<?> buscarVendaPorId(@PathVariable Long id){
        Optional<VendasDTO> venda = vendasService.getById(id);

        if(venda.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada. Tente novamente");
        }
        return ResponseEntity.ok(venda.get());

    }

    //ENDPOINT POST
    @PostMapping
    public ResponseEntity<VendasDTO> cadastrarVenda(@RequestBody VendasDTO vendasDTO) {
        VendasDTO novaVenda = vendasService.cadastrar(vendasDTO);
        return ResponseEntity.ok().body(vendasDTO);
    }

    //ENDPOINT PUT
    @PutMapping(path = "/{id}")
    public ResponseEntity<VendasModel> alterarVenda(@PathVariable Long id, @RequestBody VendasModel vendasModel){
        VendasModel venda = vendasService.alterar(id,vendasModel);

        if(venda != null){
            return ResponseEntity.ok().body(venda);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    //ENDPOINT DELETE
    @DeleteMapping(path = "/{id}")
    public void deletarVenda(@PathVariable Long id){
        vendasService.deletar(id);
    }

}
