package org.grupo3.proyectofinalspring.infraestructure.repository;

import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}
