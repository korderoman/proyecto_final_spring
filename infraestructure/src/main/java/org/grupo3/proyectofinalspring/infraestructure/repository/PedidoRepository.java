package org.grupo3.proyectofinalspring.infraestructure.repository;

import org.grupo3.proyectofinalspring.infraestructure.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
