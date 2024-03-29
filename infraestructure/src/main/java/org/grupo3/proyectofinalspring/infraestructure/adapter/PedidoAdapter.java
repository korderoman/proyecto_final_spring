package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;
import org.grupo3.proyectofinalspring.domain.ports.on.PedidoServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.PedidoEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoServiceOut {

    private final PedidoRepository pedidoRepository;

    @Override
    public PedidoDTO CrearPedidoOut(RequestPedido requestPedido) {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setId_pedido(requestPedido.getIdPedido());
        pedido.setEstado(requestPedido.getEstado());
        pedido.setFecha_inicio_pedido(getTimestamp());
        pedido.setUsuaCrea("Henry Medina");
        pedido.setDateCreate(getTimestamp());
        PedidoEntity pedidoEntity = pedidoRepository.save(pedido);
        return PedidoEntityToDto(pedidoEntity);
    }

    @Override
    public List<PedidoDTO> ObtenerTodosPedidosOut() {
        List<PedidoEntity> pedidoEntityList = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();
        for (PedidoEntity p: pedidoEntityList){
            pedidoDTOList.add(PedidoEntityToDto(p));
        }
        return pedidoDTOList;
    }

    @Override
    public Optional<PedidoDTO> BuscarPedidoPorIdOut(Long id) {
        return Optional.empty();
    }

    @Override
    public PedidoDTO ActualizarPedidoOut(Long id, RequestPedido requestPedido) {
        Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id);
        if (pedidoEntity.isEmpty()){
            return CrearPedidoOut(requestPedido);
        }

        PedidoEntity pedido = pedidoEntity.get();
        pedido.setId_pedido(requestPedido.getIdPedido());
        pedido.setEstado(requestPedido.getEstado());
        pedido.setFecha_inicio_pedido(getTimestamp());
        pedido.setUsuaModif("Henry Medina");
        pedido.setDateModif(getTimestamp());
        PedidoEntity pedidoActualizado = pedidoRepository.save(pedido);
        return PedidoEntityToDto(pedidoActualizado);
    }

    @Override
    public PedidoDTO EliminarPedidoOut(Long id) {
        boolean exists = pedidoRepository.existsById(id);
        if (exists){
            Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id);
            if (pedidoEntity.isPresent()){
                pedidoEntity.get().setEstado(0);
                pedidoEntity.get().setUsuaDelet("Henry Medina");
                pedidoEntity.get().setDateDelet(getTimestamp());
                PedidoEntity pedidoEliminado = pedidoRepository.save(pedidoEntity.get());
                return PedidoEntityToDto(pedidoEliminado);
            }
            return null;
        }
        return null;
    }

    @Override
    public PedidoDTO obtenerPedidoConClientePorOut(Long id) {
        return null;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    private PedidoDTO PedidoEntityToDto(PedidoEntity pedidoEntity){
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setIdPedido(pedidoEntity.getId_pedido());
        pedidoDTO.setFechaInicioPedido(pedidoEntity.getFecha_inicio_pedido());
        pedidoDTO.setEstado(pedidoEntity.getEstado());
        return pedidoDTO;
    }
}