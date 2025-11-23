package com.crud_simples.crud_simples.mapper;

import com.crud_simples.crud_simples.dto.usuarios.UsuarioCriacaoDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioResponseDTO;
import com.crud_simples.crud_simples.model.Usuario;

public class MapperUsuario {
    public static Usuario toEntity(UsuarioCriacaoDTO dto){
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }
    public static UsuarioResponseDTO toResponse(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        return dto;
    }
}

