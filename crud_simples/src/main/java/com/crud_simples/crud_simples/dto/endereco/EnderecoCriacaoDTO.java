package com.crud_simples.crud_simples.dto.endereco;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EnderecoCriacaoDTO {

    @NotNull
    private Long idUsuario;

    @NotBlank
    private String pais;

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;


    @NotBlank
    private String bairro;


    @NotBlank
    private String rua;


    @NotNull
    @Positive
    private Integer numero;
}
