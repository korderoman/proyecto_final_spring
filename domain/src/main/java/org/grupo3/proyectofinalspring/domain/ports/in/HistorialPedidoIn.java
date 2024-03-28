package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.HistorialPedidoDTO;
import java.util.List;
import java.util.Optional;

public interface HistorialPedidoIn {
    Optional <HistorialPedidoDTO> getHistorialPedidoByIdIn(Long idHistPedido);
    List <HistorialPedidoDTO> getAllHistorialPedidosByIdClienteIn(Long idCliente);
    List <HistorialPedidoDTO> getAllHistorialPedidosByIdPedidoIn(Long idPedido);
    List <HistorialPedidoDTO> getAllHistorialPedidosIn();

}
