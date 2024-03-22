package org.grupo3.proyectofinalspring.infraestructure.repository;

import org.grupo3.proyectofinalspring.infraestructure.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {
}
