    package com.crud_simples.crud_simples.mapper;

    import com.crud_simples.crud_simples.dto.endereco.EnderecoCriacaoDTO;
    import com.crud_simples.crud_simples.dto.endereco.EnderecoResponseDTO;
    import com.crud_simples.crud_simples.model.Endereco;
    import com.crud_simples.crud_simples.model.Usuario;

    public class MapperEndereco {



        public static Endereco toEntity(EnderecoCriacaoDTO dto){
            Endereco endereco = new Endereco();
            Usuario usuario = new Usuario();

            usuario.setId(dto.getIdUsuario());

            endereco.setUsuario(usuario);
            endereco.setPais(dto.getPais());
            endereco.setEstado(dto.getEstado());
            endereco.setCidade(dto.getCidade());
            endereco.setBairro(dto.getBairro());
            endereco.setRua(dto.getRua());
            endereco.setNumero(dto.getNumero());

            return endereco;
        }

        public static EnderecoResponseDTO toResponse(Endereco endereco){
            EnderecoResponseDTO dto = new EnderecoResponseDTO();
            Usuario usuario = endereco.getUsuario();

            dto.setId(endereco.getId());
            dto.setIdUsuario(usuario.getId());
            dto.setPais(endereco.getPais());
            dto.setEstado(endereco.getEstado());
            dto.setCidade(endereco.getCidade());
            dto.setBairro(endereco.getBairro());
            dto.setRua(endereco.getRua());
            dto.setNumero(endereco.getNumero());

            return dto;
        }
    }
