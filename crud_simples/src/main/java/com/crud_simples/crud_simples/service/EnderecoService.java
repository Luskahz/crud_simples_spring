package com.crud_simples.crud_simples.service;

import com.crud_simples.crud_simples.dto.endereco.EnderecoAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.endereco.EnderecoCriacaoDTO;
import com.crud_simples.crud_simples.mapper.MapperEndereco;
import com.crud_simples.crud_simples.model.Endereco;
import com.crud_simples.crud_simples.model.Usuario;
import com.crud_simples.crud_simples.repository.EnderecoRepository;
import com.crud_simples.crud_simples.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EnderecoService(EnderecoRepository enderecoRepository,
                           UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public Endereco criar(EnderecoCriacaoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Endereco endereco = MapperEndereco.toEntity(dto);
        endereco.setUsuario(usuario);
        return enderecoRepository.save(endereco);
    }

    // READ listar
    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    // READ buscar
    public Endereco buscar(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Endereco buscarUsuario(Long id){
        return enderecoRepository.findByUsuario_Id(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado para o Usuario: " + id));
    }

    // UPDATE atualizar
    public Endereco atualizar(Long id, EnderecoAtualizacaoDTO dto) {
        Endereco endereco = buscar(id);

        if (dto.getPais() != null && !dto.getPais().isBlank()) {
            endereco.setPais(dto.getPais());
        }

        if (dto.getEstado() != null && !dto.getEstado().isBlank()) {
            endereco.setEstado(dto.getEstado());
        }

        if (dto.getCidade() != null && !dto.getCidade().isBlank()) {
            endereco.setCidade(dto.getCidade());
        }

        if (dto.getBairro() != null && !dto.getBairro().isBlank()) {
            endereco.setBairro(dto.getBairro());
        }

        if (dto.getRua() != null && !dto.getRua().isBlank()) {
            endereco.setRua(dto.getRua());
        }

        if (dto.getNumero() != null) {
            endereco.setNumero(dto.getNumero());
        }

        return enderecoRepository.save(endereco);
    }


    // DELETE
    public void deletar(Long id) {
        Endereco endereco = buscar(id);
        enderecoRepository.delete(endereco);
    }

    public byte[] gerarCsv() {
        List<Endereco> enderecos = enderecoRepository.findAll();

        if (enderecos.isEmpty()) {
            return "Nenhum endereço cadastrado".getBytes(StandardCharsets.UTF_8);
        }

        StringBuilder sb = new StringBuilder();

        Field[] fields = Endereco.class.getDeclaredFields();
        for (Field f : fields) {
            sb.append(f.getName()).append(",");
        }

        sb.setLength(sb.length() - 1);
        sb.append("\n");

        for (Endereco e : enderecos) {
            for (Field f : fields) {
                f.setAccessible(true);

                Object valor;
                try {
                    valor = f.get(e);

                    if (valor instanceof Usuario usuario) {
                        valor = usuario.getId();
                    }

                } catch (IllegalAccessException ex) {
                    valor = "";
                }

                sb.append(valor != null ? valor : "").append(",");
            }

            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

}
