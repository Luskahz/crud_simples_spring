package com.crud_simples.crud_simples.controller;

import com.crud_simples.crud_simples.dto.documentos.DocumentosCriacaoDTO;
import com.crud_simples.crud_simples.dto.documentos.DocumentosResponseDTO;
import com.crud_simples.crud_simples.mapper.MapperDocumentos;
import com.crud_simples.crud_simples.model.Documentos;
import com.crud_simples.crud_simples.service.DocumentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentos")

public class DocumentosController {
    @Autowired
    DocumentosService documentosService;


    @PostMapping
    public DocumentosResponseDTO criar(@RequestBody DocumentosCriacaoDTO documentosDto){
        Documentos documentos = documentosService.criar(documentosDto);
        return MapperDocumentos.toResponse(documentos);
    }
}
