package com.crud_simples.crud_simples.controller;


import com.crud_simples.crud_simples.dto.usuarios.UsuarioCriacaoDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioResponseDTO;
import com.crud_simples.crud_simples.mapper.MapperUsuario;
import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.service.UsuarioService;
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
    public Usuario atualizar(@PathVariable Long id){
        usuarioService.atualizar(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        return usuarioService.deletar(id);
    }


}
