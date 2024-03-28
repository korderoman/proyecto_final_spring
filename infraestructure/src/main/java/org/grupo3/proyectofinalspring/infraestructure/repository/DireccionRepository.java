package org.grupo3.proyectofinalspring.infraestructure.repository;

import org.grupo3.proyectofinalspring.infraestructure.entity.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<DireccionEntity, Long> {
    boolean existsByEmail(String email);
}
