package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;
import org.grupo3.proyectofinalspring.domain.ports.in.PedidoServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.PedidoServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PedidoImpl implements PedidoServiceIn {
    private final PedidoServiceOut pedidoServiceOut;
    @Override
    public PedidoDTO CrearPedidoIn(RequestPedido requestPedido) {
        return pedidoServiceOut.CrearPedidoOut(requestPedido);
    }

    @Override
    public List<PedidoDTO> ObtenerTodosPedidosIn() {
        return pedidoServiceOut.ObtenerTodosPedidosOut();
    }

    @Override
    public Optional<PedidoDTO> BuscarPedidoPorIdIn(Long id) {
        return pedidoServiceOut.BuscarPedidoPorIdOut(id);
    }

    @Override
    public PedidoDTO ActualizarPedidoIn(Long id, RequestPedido requestPedido) {
        return pedidoServiceOut.ActualizarPedidoOut(id, requestPedido);
    }

    @Override
    public PedidoDTO EliminarPedidoIn(Long id) {
        return pedidoServiceOut.EliminarPedidoOut(id);
    }

    @Override
    public PedidoDTO obtenerPedidoConClientePorIdIn(Long idPedido) {
        return pedidoServiceOut.obtenerPedidoConClientePorOut(idPedido);
    }
}
