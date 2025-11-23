package com.crud_simples.crud_simples.service;

import com.crud_simples.crud_simples.dto.usuarios.UsuarioAlterarSenhaDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioCriacaoDTO;
import com.crud_simples.crud_simples.mapper.MapperUsuario;
import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public Usuario criar(UsuarioCriacaoDTO dto) {
        Usuario usuario = MapperUsuario.toEntity(dto);

        String hash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(hash);

        return usuarioRepository.save(usuario);
    }

    // READ listar
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // READ buscar
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // UPDATE
    public Usuario atualizar(Long id, UsuarioAtualizacaoDTO dto) {
        Usuario usuario = buscar(id);

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            usuario.setNome(dto.getNome());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            usuario.setEmail(dto.getEmail());
        }

        return usuarioRepository.save(usuario);
    }


    public void alterarSenha(Long id, UsuarioAlterarSenhaDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // hash seguro
        String hash = BCrypt.hashpw(dto.getSenha(), BCrypt.gensalt());

        usuario.setSenha(hash);
        usuarioRepository.save(usuario);
    }

    // DELETE
    public void deletar(Long id) {
        Usuario usuario = buscar(id);
        usuarioRepository.delete(usuario);
    }
}
