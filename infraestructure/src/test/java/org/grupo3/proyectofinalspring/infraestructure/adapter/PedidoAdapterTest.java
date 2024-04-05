package org.grupo3.proyectofinalspring.infraestructure.adapter;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;
import org.grupo3.proyectofinalspring.domain.ports.on.PedidoServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.PedidoEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.ProductoEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.ClienteRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PedidoProductoRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PedidoRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PedidoAdapterTest {
    @InjectMocks
    private PedidoAdapter pedidoAdapter;
    @Mock
    private PedidoServiceOut pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private PedidoProductoRepository pedidoProductoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void crearPedidoOutSuccess() {

    }

    @Test
    void crearPedidoOutError() {
        // Mock del servicio
        RequestPedido requestPedido = new RequestPedido(1L, 1,Collections.singletonList(1), Collections.singletonList(2));
        when(pedidoService.CrearPedidoOut(any(RequestPedido.class))).thenThrow(new RuntimeException());

        // Ejecutar método bajo prueba y verificar la excepción
        assertThrows(Exception.class, () -> pedidoAdapter.CrearPedidoOut(requestPedido));
    }
    @Test
    void obtenerTodosPedidosOutSuccess() {
        // Mock del servicio
        List<PedidoDTO> pedidoDTOListExpected = new ArrayList<>(); // crear lista ficticia de DTOs
        when(pedidoService.ObtenerTodosPedidosOut()).thenReturn(pedidoDTOListExpected);

        // Ejecutar método bajo prueba
        List<PedidoDTO> pedidoDTOList = pedidoAdapter.ObtenerTodosPedidosOut();

        // Verificar resultados
        assertNotNull(pedidoDTOList);
        assertEquals(pedidoDTOListExpected, pedidoDTOList);
    }



    @Test
    void actualizarPedidoOutError() {
        // Mock del servicio
        RequestPedido requestPedido = new RequestPedido(1L, 1,Collections.singletonList(1), Collections.singletonList(2));
        when(pedidoService.ActualizarPedidoOut(anyLong(), any(RequestPedido.class))).thenThrow(new RuntimeException());

        // Ejecutar método bajo prueba y verificar la excepción
        assertThrows(Exception.class, () -> pedidoAdapter.ActualizarPedidoOut(1L, requestPedido));
    }



    @Test
    void eliminarPedidoOutError() {
        // Mock del servicio
        when(pedidoService.EliminarPedidoOut(anyLong())).thenReturn(null);

        // Ejecutar método bajo prueba
        PedidoDTO pedidoDTO = pedidoAdapter.EliminarPedidoOut(1L);

        // Verificar resultados
        assertNull(pedidoDTO);
    }
}