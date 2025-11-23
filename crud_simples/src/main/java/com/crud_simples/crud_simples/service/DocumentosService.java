package com.crud_simples.crud_simples.service;

import com.crud_simples.crud_simples.dto.documentos.DocumentosAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.documentos.DocumentosCriacaoDTO;
import com.crud_simples.crud_simples.mapper.MapperDocumentos;
import com.crud_simples.crud_simples.model.Documentos;
import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.repository.DocumentosRepository;
import com.crud_simples.crud_simples.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentosService {

    private final DocumentosRepository documentosRepository;
    private final UsuarioRepository usuarioRepository;

    public DocumentosService(DocumentosRepository documentosRepository,
                             UsuarioRepository usuarioRepository) {
        this.documentosRepository = documentosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public Documentos criar(DocumentosCriacaoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Documentos documentos = MapperDocumentos.toEntity(dto);
        documentos.setUsuario(usuario);

        return documentosRepository.save(documentos);
    }

    // READ listar
    public List<Documentos> listar() {
        return documentosRepository.findAll();
    }

    // READ buscar
    public Documentos buscar(Long id) {
        return documentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documentos não encontrados"));
    }

    public Documentos buscarUsuario(Long id){
        return documentosRepository.findByUsuario_Id(id)
                .orElseThrow(() -> new RuntimeException("Documentos não encontrados para o Usuario: " + id));
    }
    // UPDATE
    public Documentos atualizar(Long id, DocumentosAtualizacaoDTO dto) {
        Documentos documentos = buscar(id);

        if (dto.getCpf() != null && !dto.getCpf().isBlank()) {
            documentos.setCpf(dto.getCpf());
        }

        if (dto.getRg() != null && !dto.getRg().isBlank()) {
            documentos.setRg(dto.getRg());
        }

        if (dto.getCnh() != null && !dto.getCnh().isBlank()) {
            documentos.setCnh(dto.getCnh());
        }

        return documentosRepository.save(documentos);
    }


    // DELETE
    public void deletar(Long id) {
        Documentos documentos = buscar(id);
        documentosRepository.delete(documentos);
    }
}
