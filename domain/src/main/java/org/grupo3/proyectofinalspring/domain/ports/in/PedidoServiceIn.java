package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceIn {
    PedidoDTO CrearPedidoIn(RequestPedido requestPedido);
    List<PedidoDTO> ObtenerTodosPedidosIn();
    Optional<PedidoDTO> BuscarPedidoPorIdIn(Long id);
    PedidoDTO ActualizarPedidoIn(Long id, RequestPedido requestPedido);
    PedidoDTO EliminarPedidoIn(Long id);

    PedidoDTO obtenerPedidoConClientePorIdIn(Long id);
}
