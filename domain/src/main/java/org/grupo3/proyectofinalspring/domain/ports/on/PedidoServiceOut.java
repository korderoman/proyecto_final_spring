package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceOut {
    PedidoDTO CrearPedidoOut(RequestPedido requestPedido);
    List<PedidoDTO> ObtenerTodosPedidosOut();
    Optional<PedidoDTO> BuscarPedidoPorIdOut(Long id);
    PedidoDTO ActualizarPedidoOut(Long id, RequestPedido requestPedido);
    PedidoDTO EliminarPedidoOut(Long id);
    PedidoDTO obtenerPedidoConClientePorOut(Long id);

}
