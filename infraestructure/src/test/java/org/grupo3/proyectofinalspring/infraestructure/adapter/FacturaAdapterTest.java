package org.grupo3.proyectofinalspring.infraestructure.adapter;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;
import org.grupo3.proyectofinalspring.infraestructure.entity.FacturaEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.PedidoEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.FacturaRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


class FacturaAdapterTest {
    @Mock
    private FacturaRepository facturaRepository;
    @Mock
    private PedidoRepository pedidoRepository;
    @InjectMocks
    private FacturaAdapter facturaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addFacturaOutSuccess() {
        // Mock para requestFactura
        RequestFactura requestFactura = new RequestFactura(123456L, "2", 3,0.8, new Timestamp(System.currentTimeMillis()), 1);
        FacturaEntity facturaEntityEsperada = getMockFactura();
        // Configurar comportamiento del repositorio
        when(pedidoRepository.findById(anyLong())).thenReturn(Optional.of(getMockPedido()));
        when(facturaRepository.save(any(FacturaEntity.class))).thenReturn(facturaEntityEsperada);

        // Ejecutar el método bajo prueba
        FacturaDTO facturaDTORecibida = facturaAdapter.addFacturaOut(requestFactura);

        // Verificar resultados
        assertEquals(facturaEntityEsperada.getId_factura(), facturaDTORecibida.getIdFactura());
        assertEquals(requestFactura.getNumFactura(), facturaDTORecibida.getNumFactura());
        assertEquals(requestFactura.getCantidad(), facturaDTORecibida.getCantidad());
        assertEquals(requestFactura.getIgv(), facturaDTORecibida.getIgv());
    }

    @Test
    void addFacturaOutError() {
        // Mock para requestFactura
        RequestFactura requestFactura = new RequestFactura(123456L, "2", 3,0.8, new Timestamp(System.currentTimeMillis()), 1);

        // Configurar comportamiento del repositorio
        when(facturaRepository.save(any(FacturaEntity.class))).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> facturaAdapter.addFacturaOut(requestFactura));
    }

    @Test
    void getAllFacturaOutSuccess() {
        // Mock para el repositorio
        when(facturaRepository.findAll()).thenReturn(getMockFacturaList());

        // Ejecutar el método bajo prueba
        assertEquals(2, facturaAdapter.getAllFacturaOut().size());
    }

    @Test
    void getAllFacturaOutError() {
        // Mock para el repositorio
        when(facturaRepository.findAll()).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> facturaAdapter.getAllFacturaOut());
    }

    @Test
    void getFacturaByIdOutSuccess() throws Exception {
        // Mock para el repositorio
        FacturaEntity facturaEntityEsperada = getMockFactura();
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(facturaEntityEsperada));

        // Ejecutar el método bajo prueba
        FacturaDTO facturaDTORecibida = facturaAdapter.getFacturaByIdOut(1L);

        // Verificar resultados
        assertEquals(facturaEntityEsperada.getId_factura(), facturaDTORecibida.getIdFactura());
        assertEquals(facturaEntityEsperada.getNum_factura(), facturaDTORecibida.getNumFactura());
        assertEquals(facturaEntityEsperada.getCantidad(), facturaDTORecibida.getCantidad());
        assertEquals(facturaEntityEsperada.getIgv(), facturaDTORecibida.getIgv());
    }

    @Test
    void getFacturaByIdOutError() {
        // Mock para el repositorio
        when(facturaRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> facturaAdapter.getFacturaByIdOut(1L));
    }

    @Test
    void updateFacturaByIdOutSuccess() {
        // Mock para requestFactura
        RequestFactura requestFactura = new RequestFactura(123456L, "2", 3,0.8, new Timestamp(System.currentTimeMillis()), 1);
        FacturaEntity facturaEntityEsperada = getMockFactura();

        // Configurar comportamiento del repositorio
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(facturaEntityEsperada));
        when(facturaRepository.save(any(FacturaEntity.class))).thenReturn(facturaEntityEsperada);

        // Ejecutar el método bajo prueba
        FacturaDTO facturaDTORecibida = facturaAdapter.updateFacturaByIdOut(1L, requestFactura);

        // Verificar resultados
        assertEquals(facturaEntityEsperada.getId_factura(), facturaDTORecibida.getIdFactura());
        assertEquals(requestFactura.getNumFactura(), facturaDTORecibida.getNumFactura());
        assertEquals(requestFactura.getCantidad(), facturaDTORecibida.getCantidad());
        assertEquals(requestFactura.getIgv(), facturaDTORecibida.getIgv());
    }

    @Test
    void updateFacturaByIdOutError() {
        // Mock para requestFactura
        RequestFactura requestFactura = new RequestFactura(123456L, "2", 3,0.8, new Timestamp(System.currentTimeMillis()), 1);

        // Configurar comportamiento del repositorio
        when(facturaRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> facturaAdapter.updateFacturaByIdOut(1L, requestFactura));
    }

    @Test
    void deleteFacturaByIdOutSuccess() throws Exception {
        // Mock para el repositorio
        FacturaEntity facturaEntityEsperada = getMockFactura();
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(facturaEntityEsperada));
        when(facturaRepository.existsById(1L)).thenReturn(true);
        when(facturaRepository.save(any(FacturaEntity.class))).thenReturn(facturaEntityEsperada);

        // Ejecutar el método bajo prueba
        FacturaDTO facturaDTORecibida = facturaAdapter.deleteFacturaByIdOut(1L);

        // Verificar resultados
        assertEquals(facturaEntityEsperada.getId_factura(), facturaDTORecibida.getIdFactura());
        assertEquals(facturaEntityEsperada.getNum_factura(), facturaDTORecibida.getNumFactura());
        assertEquals(facturaEntityEsperada.getCantidad(), facturaDTORecibida.getCantidad());
        assertEquals(facturaEntityEsperada.getIgv(), facturaDTORecibida.getIgv());
    }

    private List<FacturaEntity> getMockFacturaList() {
        List<FacturaEntity> facturaEntities = new ArrayList<>();
        facturaEntities.add(getMockFactura());
        facturaEntities.add(getMockFactura());
        return facturaEntities;
    }

    private FacturaEntity getMockFactura() {
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setId_factura(123456L);
        facturaEntity.setNum_factura("2");
        facturaEntity.setCantidad(3);
        facturaEntity.setIgv(0.8);
        return facturaEntity;
    }
    private PedidoEntity getMockPedido() {
        PedidoEntity pedido = new PedidoEntity();
        // Configura las propiedades del pedido según lo necesites para tu prueba
        pedido.setId_pedido(123L);
        pedido.setDateCreate(new Timestamp(System.currentTimeMillis()));
        // Configura otras propiedades si es necesario
        return pedido;
    }
}