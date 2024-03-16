package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCliente;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceOut {
    UsuarioDTO crearPersonaOut(RequestCliente requestCliente);
    Optional<UsuarioDTO> obtenerPersonaOut(Long numDoc);
    List<UsuarioDTO> obtenerTodosOut();
    UsuarioDTO actualizarOut(Long id, RequestCliente requestCliente);
    UsuarioDTO deleteOut(Long id);
}
