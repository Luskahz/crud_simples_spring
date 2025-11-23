package com.crud_simples.crud_simples.controller;

import com.crud_simples.crud_simples.dto.documentos.DocumentosAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.documentos.DocumentosCriacaoDTO;
import com.crud_simples.crud_simples.dto.documentos.DocumentosResponseDTO;
import com.crud_simples.crud_simples.dto.endereco.EnderecoResponseDTO;
import com.crud_simples.crud_simples.mapper.MapperDocumentos;
import com.crud_simples.crud_simples.mapper.MapperEndereco;
import com.crud_simples.crud_simples.model.Documentos;
import com.crud_simples.crud_simples.model.Endereco;
import com.crud_simples.crud_simples.service.DocumentosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentosController {

    private final DocumentosService documentosService;

    public DocumentosController(DocumentosService documentosService) {
        this.documentosService = documentosService;
    }

    @PostMapping
    public DocumentosResponseDTO criar(@RequestBody @Valid DocumentosCriacaoDTO dto){
        Documentos documentos = documentosService.criar(dto);
        return MapperDocumentos.toResponse(documentos);
    }

    @GetMapping
    public List<DocumentosResponseDTO> listar(){
        return documentosService.listar()
                .stream()
                .map(MapperDocumentos::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public DocumentosResponseDTO buscar(@PathVariable Long id){
        Documentos documentos = documentosService.buscar(id);
        return MapperDocumentos.toResponse(documentos);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<DocumentosResponseDTO> buscarUsuario(@PathVariable Long id){
        try {
            Documentos documentos = documentosService.buscarUsuario(id);
            return ResponseEntity.ok(MapperDocumentos.toResponse(documentos));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public DocumentosResponseDTO atualizar(@PathVariable Long id,
                                           @RequestBody @Valid DocumentosAtualizacaoDTO dto){
        Documentos documentos = documentosService.atualizar(id, dto);
        return MapperDocumentos.toResponse(documentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        documentosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
