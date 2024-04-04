package org.grupo3.proyectofinalspring.infraestructure.adapter;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;
import org.grupo3.proyectofinalspring.infraestructure.entity.CaracteristicasEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CaracteristicasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CaracteristicasAdapterTest {
    @Mock
    private CaracteristicasRepository caracteristicasRepository;
    @InjectMocks
    private CaracteristicasAdapter caracteristicasAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCaracteristicsOutSuccess() {
        // Mock para requestCaracteristica
        RequestCaracteristicas requestCaracteristicas = new RequestCaracteristicas("zapatillas deportivas adidas talla 42", 1);
        CaracteristicasEntity caracteristicasEntityEsperada = getCaracteristicas();
        // Configurar comportamiento del repositorio
        when(caracteristicasRepository.save(any(CaracteristicasEntity.class))).thenReturn(caracteristicasEntityEsperada);

        // Ejecutar el método bajo prueba
        CaracteristicasDTO caracteristicasDTORecibida = caracteristicasAdapter.addCaracteristicsOut(requestCaracteristicas);

        // Verificar resultados
        assertEquals(caracteristicasEntityEsperada.getId_caracteristicas(), caracteristicasDTORecibida.getIdCaracteristicas());
        assertEquals(caracteristicasEntityEsperada.getEstado(), caracteristicasDTORecibida.getEstado());
        assertEquals(caracteristicasEntityEsperada.getDescripcion(), caracteristicasDTORecibida.getDescripcion());
    }

    @Test
    void addCaracteristicsOutError() {
        // Mock para requestCaracteristica
        RequestCaracteristicas requestCaracteristicas = new RequestCaracteristicas("zapatillas deportivas adidas talla 41", 1);
        // Configurar comportamiento del repositorio
        when(caracteristicasRepository.save(any(CaracteristicasEntity.class))).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> caracteristicasAdapter.addCaracteristicsOut(requestCaracteristicas));
    }

    @Test
    void getAllCaracteristicsOutSuccess() {
        // Mock para el repositorio
        when(caracteristicasRepository.findAll()).thenReturn(getCaracteristicasEntityList());

        // Ejecutar el método bajo prueba
        assertEquals(2, caracteristicasAdapter.getAllCaracteristicsOut().size());
    }
    @Test
    void getAllCaracteristicsOutError() {
        // Mock para el repositorio
        when(caracteristicasRepository.findAll()).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> caracteristicasAdapter.getAllCaracteristicsOut());
    }
    @Test
    void getCaracteristicsByIdOutSuccess() throws Exception {
        // Mock para el repositorio
        CaracteristicasEntity caracteristicasEntityEsperada = getCaracteristicas();
        when(caracteristicasRepository.findById(1L)).thenReturn(Optional.of(caracteristicasEntityEsperada));

        // Ejecutar el método bajo prueba
        CaracteristicasDTO caracteristicasDTORecibida = caracteristicasAdapter.getCaracteristicsByIdOut(1L);

        // Verificar resultados
        assertEquals(caracteristicasEntityEsperada.getId_caracteristicas(), caracteristicasDTORecibida.getIdCaracteristicas());
        assertEquals(caracteristicasEntityEsperada.getEstado(), caracteristicasDTORecibida.getEstado());
        assertEquals(caracteristicasEntityEsperada.getDescripcion(), caracteristicasDTORecibida.getDescripcion());
    }

    @Test
    void getCaracteristicsByIdOutError() {
        // Mock para el repositorio
        when(caracteristicasRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> caracteristicasAdapter.getCaracteristicsByIdOut(1L));
    }

    @Test
    void updateCaracteristicsByIdOutSuccess() {
        // Mock para requestCaracteristica
        RequestCaracteristicas requestCaracteristicas = new RequestCaracteristicas("zapatillas deportivas adidas talla 42", 1);
        CaracteristicasEntity caracteristicasEntityEsperada = getCaracteristicas();
        // Configurar comportamiento del repositorio
        when(caracteristicasRepository.findById(1L)).thenReturn(Optional.of(caracteristicasEntityEsperada));
        when(caracteristicasRepository.save(any(CaracteristicasEntity.class))).thenReturn(caracteristicasEntityEsperada);

        // Ejecutar el método bajo prueba
        CaracteristicasDTO caracteristicasDTORecibida = caracteristicasAdapter.updateCaracteristicsByIdOut(1L, requestCaracteristicas);

        // Verificar resultados
        assertEquals(caracteristicasEntityEsperada.getId_caracteristicas(), caracteristicasDTORecibida.getIdCaracteristicas());
        assertEquals(caracteristicasEntityEsperada.getEstado(), caracteristicasDTORecibida.getEstado());
        assertEquals(caracteristicasEntityEsperada.getDescripcion(), caracteristicasDTORecibida.getDescripcion());
    }
    @Test
    void updateCaracteristicsByIdOutError() {
        // Mock para requestCaracteristica
        RequestCaracteristicas requestCaracteristicas = new RequestCaracteristicas("zapatillas deportivas adidas talla 42", 1);
        // Configurar comportamiento del repositorio
        when(caracteristicasRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> caracteristicasAdapter.updateCaracteristicsByIdOut(1L, requestCaracteristicas));
    }
    @Test
    void deleteCaracteristicsByIdOutSuccess() throws Exception {
        // Mock para el repositorio
        CaracteristicasEntity caracteristicasEntityEsperada = getCaracteristicas();
        when(caracteristicasRepository.findById(1L)).thenReturn(Optional.of(caracteristicasEntityEsperada));
        when(caracteristicasRepository.existsById(1L)).thenReturn(true);
        when(caracteristicasRepository.save(any(CaracteristicasEntity.class))).thenReturn(caracteristicasEntityEsperada);

        // Ejecutar el método bajo prueba
        CaracteristicasDTO caracteristicasDTORecibida = caracteristicasAdapter.deleteCaracteristicsByIdOut(1L);

        // Verificar resultados
        assertEquals(caracteristicasEntityEsperada.getId_caracteristicas(), caracteristicasDTORecibida.getIdCaracteristicas());
        assertEquals(caracteristicasEntityEsperada.getEstado(), caracteristicasDTORecibida.getEstado());
        assertEquals(caracteristicasEntityEsperada.getDescripcion(), caracteristicasDTORecibida.getDescripcion());
    }

    @Test
    void deleteCaracteristicsByIdOutError() {
        // Mock para el repositorio
        when(caracteristicasRepository.existsById(1L)).thenReturn(false);

        // Verificar resultados
        assertThrows(Exception.class, () -> caracteristicasAdapter.deleteCaracteristicsByIdOut(1L));
    }

    private List<CaracteristicasEntity> getCaracteristicasEntityList() {
        List<CaracteristicasEntity> caracteristicasEntities = new ArrayList<>();
        caracteristicasEntities.add(getCaracteristicas());
        caracteristicasEntities.add(getCaracteristicas());
        return caracteristicasEntities;
    }

    private CaracteristicasEntity getCaracteristicas() {
        CaracteristicasEntity caracteristicasEntityEsperada = new CaracteristicasEntity();
        caracteristicasEntityEsperada.setId_caracteristicas(1L);
        caracteristicasEntityEsperada.setDescripcion("zapatillas deportivas adidas talla 42");
        caracteristicasEntityEsperada.setEstado(1);
        return caracteristicasEntityEsperada;
    }
}