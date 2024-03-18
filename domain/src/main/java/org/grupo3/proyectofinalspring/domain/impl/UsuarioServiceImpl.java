package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCliente;
import org.grupo3.proyectofinalspring.domain.ports.in.UsuarioServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.UsuarioServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioServiceIn {
    private final UsuarioServiceOut usuarioServiceOut;


    @Override
    public UsuarioDTO crearPersonaIn(RequestCliente requestCliente) {
        return usuarioServiceOut.crearPersonaOut(requestCliente);
    }

    @Override
    public Optional<UsuarioDTO> obtenerPersonaIn(Long numDoc) {
        return usuarioServiceOut.obtenerPersonaOut(numDoc);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosIn() {
        return usuarioServiceOut.obtenerTodosOut();
    }

    @Override
    public UsuarioDTO actualizarIn(Long id, RequestCliente requestCliente) {
        return usuarioServiceOut.actualizarOut(id, requestCliente);
    }

    @Override
    public UsuarioDTO deleteIn(Long id) {
        return usuarioServiceOut.deleteOut(id);
    }
}
