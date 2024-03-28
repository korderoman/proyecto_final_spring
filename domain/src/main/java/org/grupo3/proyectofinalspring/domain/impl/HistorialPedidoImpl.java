package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.HistorialPedidoDTO;
import org.grupo3.proyectofinalspring.domain.ports.in.HistorialPedidoIn;
import org.grupo3.proyectofinalspring.domain.ports.on.HistorialPedidoOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistorialPedidoImpl implements HistorialPedidoIn {
    private final HistorialPedidoOut historialPedidoOut;
    @Override
    public Optional<HistorialPedidoDTO> getHistorialPedidoByIdIn(Long idHistPedido) {
        return historialPedidoOut.getHistorialPedidoByIdOut(idHistPedido);
    }

    @Override
    public List<HistorialPedidoDTO> getAllHistorialPedidosByIdClienteIn(Long idCliente) {
        return historialPedidoOut.getAllHistorialPedidosByIdClienteOut(idCliente);
    }

    @Override
    public List<HistorialPedidoDTO> getAllHistorialPedidosByIdPedidoIn(Long idPedido) {
        return historialPedidoOut.getAllHistorialPedidosByIdPedidoOut(idPedido);
    }

    @Override
    public List<HistorialPedidoDTO> getAllHistorialPedidosIn() {
        return historialPedidoOut.getAllHistorialPedidosOut();
    }
}
