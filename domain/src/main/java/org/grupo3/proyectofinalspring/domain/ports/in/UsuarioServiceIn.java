package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCliente;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceIn {
    UsuarioDTO crearPersonaIn(RequestCliente requestCliente);
    Optional<UsuarioDTO> obtenerPersonaIn(Long numDoc);
    List<UsuarioDTO> obtenerTodosIn();
    UsuarioDTO actualizarIn(Long id, RequestCliente requestCliente);
    UsuarioDTO deleteIn(Long id);
}
