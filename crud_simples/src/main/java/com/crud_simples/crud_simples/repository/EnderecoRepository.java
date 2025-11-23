package com.crud_simples.crud_simples.repository;

import com.crud_simples.crud_simples.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByUsuario_Id(Long idUsuario);
}
