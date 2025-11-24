package com.crud_simples.crud_simples.service;

import com.crud_simples.crud_simples.dto.usuarios.UsuarioAlterarSenhaDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.usuarios.UsuarioCriacaoDTO;
import com.crud_simples.crud_simples.mapper.MapperUsuario;
import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.awt.*;

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


    public byte[] gerarPdfUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, out);
            pdf.open();

            Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font campoFont = new Font(Font.HELVETICA, 12);

            Paragraph titulo = new Paragraph("Lista de Usuários\n\n", tituloFont);
            pdf.add(titulo);

            for (Usuario u : usuarios) {
                pdf.add(new Paragraph("ID: " + u.getId(), campoFont));
                pdf.add(new Paragraph("Nome: " + u.getNome(), campoFont));
                pdf.add(new Paragraph("Email: " + u.getEmail(), campoFont));
                pdf.add(new Paragraph("-----------------------------------------\n", campoFont));
            }

            pdf.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return out.toByteArray();
    }
}
