package com.crud_simples.crud_simples.dto.usuarios;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioCriacaoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
