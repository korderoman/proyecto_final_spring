package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.HistorialPedidoDTO;
import org.grupo3.proyectofinalspring.domain.ports.on.HistorialPedidoOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistorialPedidoAdapter implements HistorialPedidoOut {
    @Override
    public Optional<HistorialPedidoDTO> getHistorialPedidoByIdOut(Long idHistPedido) {

        return Optional.empty();
    }

    @Override
    public List<HistorialPedidoDTO> getAllHistorialPedidosByIdClienteOut(Long idCliente) {
        return null;
    }

    @Override
    public List<HistorialPedidoDTO> getAllHistorialPedidosByIdPedidoOut(Long idPedido) {
        return null;
    }

    @Override
    public List<HistorialPedidoDTO> getAllHistorialPedidosOut() {
        return null;
    }
}
