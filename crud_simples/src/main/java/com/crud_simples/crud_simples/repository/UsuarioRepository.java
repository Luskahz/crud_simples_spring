package com.crud_simples.crud_simples.repository;

import com.crud_simples.crud_simples.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
