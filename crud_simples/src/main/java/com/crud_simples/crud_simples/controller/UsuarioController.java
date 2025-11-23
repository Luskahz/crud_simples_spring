package com.crud_simples.crud_simples.controller;


import com.crud_simples.crud_simples.dto.usuarios.UsuarioAlterarSenhaDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioCriacaoDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioResponseDTO;
import com.crud_simples.crud_simples.mapper.MapperUsuario;
import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.service.UsuarioService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public UsuarioResponseDTO criar(@RequestBody UsuarioCriacaoDTO dto){
        Usuario usuario =  usuarioService.criar(dto);
        return MapperUsuario.toResponse(usuario);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar(){
        List<Usuario> usuarios = usuarioService.listar();
        List<UsuarioResponseDTO> usuariosDto;

        return usuarios.stream()
                .map(MapperUsuario::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscar(@PathVariable Long id){
        Usuario usuario = usuarioService.buscar(id);
        return MapperUsuario.toResponse(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable Long id, @RequestBody UsuarioAtualizacaoDTO dto){
        Usuario usuario = usuarioService.atualizar(id, dto);
        return MapperUsuario.toResponse(usuario);
    }

    @PatchMapping("/alterarSenha/{id}")
    public ResponseEntity<Void> alterarSenha(@PathVariable Long id, @RequestBody UsuarioAlterarSenhaDTO dto){
        usuarioService.alterarSenha(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
