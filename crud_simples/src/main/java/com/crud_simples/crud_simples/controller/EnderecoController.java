package com.crud_simples.crud_simples.controller;

import com.crud_simples.crud_simples.dto.documentos.DocumentosCriacaoDTO;
import com.crud_simples.crud_simples.dto.endereco.EnderecoCriacaoDTO;
import com.crud_simples.crud_simples.model.Documentos;
import com.crud_simples.crud_simples.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Enderecos")

public class EnderecoController {
    @Autowired
    private com.crud_simples.crud_simples.service.EnderecoService enderecoService;


    @PostMapping
    public Endereco criar(@RequestBody EnderecoCriacaoDTO enderecoDto){
        return enderecoService.criar(enderecoDto);
    }
}