package com.crud_simples.crud_simples.mapper;

import com.crud_simples.crud_simples.dto.documentos.DocumentosCriacaoDTO;
import com.crud_simples.crud_simples.dto.documentos.DocumentosResponseDTO;
import com.crud_simples.crud_simples.model.Documentos;
import com.crud_simples.crud_simples.model.Usuario;

public class MapperDocumentos {


    public static Documentos toEntity(DocumentosCriacaoDTO dto){
        Documentos documentos = new Documentos();
        Usuario usuario = new Usuario();

        usuario.setId(dto.getIdUsuario());
        documentos.setUsuario(usuario);
        documentos.setCpf(dto.getCpf());
        documentos.setRg(dto.getRg());
        documentos.setCnh(dto.getCnh());

        return documentos;
    }

    public static DocumentosResponseDTO toResponse(Documentos documentos){
        DocumentosResponseDTO dto = new DocumentosResponseDTO();
        Usuario usuario = documentos.getUsuario();

        dto.setId(documentos.getId());
        dto.setIdUsuario(usuario.getId());
        dto.setCpf(documentos.getCpf());
        dto.setRg(documentos.getRg());
        dto.setCnh(documentos.getCnh());

        return dto;
    }
}
