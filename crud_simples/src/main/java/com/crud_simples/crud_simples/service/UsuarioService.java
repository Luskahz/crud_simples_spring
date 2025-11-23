package com.crud_simples.crud_simples.service;

import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.repository.DocumentosRepository;
import com.crud_simples.crud_simples.repository.EnderecoRepository;
import com.crud_simples.crud_simples.repository.UsuarioRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private DocumentosRepository documentosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criar(Usuario usuarioref){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioref.getEmail());
        usuario.setNome(usuarioref.getNome());
        usuario.setSenha(usuarioref.getSenha());
    }




}
