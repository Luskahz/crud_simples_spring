package com.crud_simples.crud_simples.dto.documentos;


import lombok.Data;

@Data
public class DocumentosResponseDTO {
    private Long id;
    private Long idUsuario;
    private String cpf;
    private String rg;
    private String cnh;

}
