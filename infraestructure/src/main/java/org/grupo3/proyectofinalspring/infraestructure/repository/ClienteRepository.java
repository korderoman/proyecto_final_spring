package org.grupo3.proyectofinalspring.infraestructure.repository;

import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    boolean existsByDni(String Dni);
}
