package com.crud_simples.crud_simples.repository;

import com.crud_simples.crud_simples.model.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentosRepository extends JpaRepository<Documentos, Long> {
}
