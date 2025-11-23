package com.crud_simples.crud_simples.dto.endereco;


import lombok.Data;

@Data
public class EnderecoResponseDTO {
    private Long id;
    private Long idUsuario;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;
}
