package com.crud_simples.crud_simples.controller;

import com.crud_simples.crud_simples.model.Documentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Documentos")

public class DocumentosController {
    @Autowired
    private com.crud_simples.crud_simples.service.DocumentosService documentosService;

    public Documentos criar(@RequestBody Documentos documentos){
        return documentosService.criar(documentos)
    }
}
