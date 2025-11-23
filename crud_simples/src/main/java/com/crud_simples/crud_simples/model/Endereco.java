package com.crud_simples.crud_simples.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table (name = "Endereco")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Endereco {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario Usuario;


    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
}
