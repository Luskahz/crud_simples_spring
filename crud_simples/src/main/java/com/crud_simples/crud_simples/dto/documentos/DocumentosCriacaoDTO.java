package com.crud_simples.crud_simples.dto.documentos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class DocumentosCriacaoDTO {
    @NotNull
    private Long idUsuario;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    private String cnh;

}
