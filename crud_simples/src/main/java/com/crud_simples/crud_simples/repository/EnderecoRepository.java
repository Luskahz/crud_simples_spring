package com.crud_simples.crud_simples.repository;

import com.crud_simples.crud_simples.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
