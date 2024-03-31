package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;
import org.grupo3.proyectofinalspring.domain.ports.on.PedidoServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.PedidoEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.PedidoProductoEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.ProductoEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.ClienteRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PedidoProductoRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PedidoRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoAdapter implements PedidoServiceOut {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    @Override
    public PedidoDTO CrearPedidoOut(RequestPedido requestPedido) {


        PedidoEntity pedido = new PedidoEntity();

        ClienteEntity clienteEntity = getCliente(requestPedido.getIdCliente());

        pedido.setCliente(clienteEntity);
        pedido.setEstado(requestPedido.getEstado());
        pedido.setFecha_inicio_pedido(getTimestamp());
        pedido.setUsuaCrea("Henry Medina");
        pedido.setDateCreate(getTimestamp());

        PedidoEntity pedidoEntity = pedidoRepository.save(pedido);

        for(int pos = 0; pos < requestPedido.getIdsProductos().size(); pos++){
            Long idProducto = Long.valueOf(requestPedido.getIdsProductos().get(pos));
            ProductoEntity productoEntity = getProducto(idProducto);
            PedidoProductoEntity pedidoProductoEntity = new PedidoProductoEntity();
            pedidoProductoEntity.setPedido(pedidoEntity);
            pedidoProductoEntity.setProducto(productoEntity);
            pedidoProductoEntity.setCantidad(requestPedido.getCantidadesProductos().get(pos));
            pedidoProductoEntity.setPrecio(productoEntity.getPrecio());
            pedidoProductoRepository.save(pedidoProductoEntity);
        }


        return PedidoEntityToDto(pedidoEntity);
    }

    @Override
    public List<PedidoDTO> ObtenerTodosPedidosOut() {
        List<PedidoEntity> pedidoEntityList = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();
        for (PedidoEntity p : pedidoEntityList) {
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
        if (pedidoEntity.isEmpty()) {
            return CrearPedidoOut(requestPedido);
        }

        PedidoEntity pedido = pedidoEntity.get();
        //pedido.setCliente(requestPedido.);
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
        if (exists) {
            Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id);
            if (pedidoEntity.isPresent()) {
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

    private Timestamp getTimestamp() {
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    private PedidoDTO PedidoEntityToDto(PedidoEntity pedidoEntity) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setIdPedido(pedidoEntity.getId_pedido());
       // pedidoDTO.setIdCliente(pedidoEntity.getId_cliente());
        pedidoDTO.setFechaInicioPedido(pedidoEntity.getFecha_inicio_pedido());
        pedidoDTO.setEstado(pedidoEntity.getEstado());
        return pedidoDTO;
    }

    private ClienteEntity getCliente(Long id_cliente) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id_cliente);
        if (clienteEntity.isPresent()) {
            return clienteEntity.get();
        }
        return null;
    }

    private ProductoEntity getProducto(Long id_producto){
        Optional<ProductoEntity> productoEntity = productoRepository.findById(id_producto);
        if (productoEntity.isPresent()) {
            return productoEntity.get();
        }
        return null;
    }
}