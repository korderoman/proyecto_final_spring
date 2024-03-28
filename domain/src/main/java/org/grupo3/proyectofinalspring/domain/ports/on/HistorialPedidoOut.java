package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.HistorialPedidoDTO;

import java.util.List;
import java.util.Optional;

public interface HistorialPedidoOut {
    Optional <HistorialPedidoDTO> getHistorialPedidoByIdOut(Long idHistPedido);
    List <HistorialPedidoDTO> getAllHistorialPedidosByIdClienteOut(Long idCliente);
    List <HistorialPedidoDTO> getAllHistorialPedidosByIdPedidoOut(Long idPedido);
    List <HistorialPedidoDTO> getAllHistorialPedidosOut();
}
