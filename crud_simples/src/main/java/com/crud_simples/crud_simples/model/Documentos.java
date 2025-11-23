package com.crud_simples.crud_simples.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;

@Entity
@Table (name = "Documentos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Documentos {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String cpf;
    private String rg;
    private String cnh;
}