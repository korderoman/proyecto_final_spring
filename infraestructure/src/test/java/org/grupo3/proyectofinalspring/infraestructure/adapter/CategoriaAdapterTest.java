package org.grupo3.proyectofinalspring.infraestructure.adapter;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;
import org.grupo3.proyectofinalspring.infraestructure.entity.CategoriaEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CategoriaRepository;
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
import static org.mockito.Mockito.when;

class CategoriaAdapterTest {
    @Mock
    private CategoriaRepository categoriaRepository;
    @InjectMocks
    private CategoriaAdapter categoriaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCategoryOutSuccess() {
        // Mock para requestCategoria
        RequestCategoria requestCategoria = new RequestCategoria("Ropa", 1);
        CategoriaEntity categoriaEntityEsperada = getMockCategoria();

        // Configurar comportamiento del repositorio
        when(categoriaRepository.save(any(CategoriaEntity.class))).thenReturn(categoriaEntityEsperada);

        // Ejecutar el método bajo prueba
        CategoriaDTO categoriaDTORecibida = categoriaAdapter.addCategoryOut(requestCategoria);

        // Verificar resultados
        assertEquals(categoriaEntityEsperada.getId_categoria(), categoriaDTORecibida.getIdCategoria());
        assertEquals(categoriaEntityEsperada.getEstado(), categoriaDTORecibida.getEstado());
        assertEquals(categoriaEntityEsperada.getDescripcion(), categoriaDTORecibida.getDescripcion());
    }

    @Test
    void addCategoryOutError() {
        // Mock para requestCategoria
        RequestCategoria requestCategoria = new RequestCategoria("Ropa", 1);

        // Configurar comportamiento del repositorio
        when(categoriaRepository.save(any(CategoriaEntity.class))).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> categoriaAdapter.addCategoryOut(requestCategoria));
    }

    @Test
    void getAllCategoriesOutSuccess() {
        // Mock para el repositorio
        when(categoriaRepository.findAll()).thenReturn(getMockCategoriaList());

        // Ejecutar el método bajo prueba
        assertEquals(2, categoriaAdapter.getAllCategoriesOut().size());
    }

    @Test
    void getAllCategoriesOutError() {
        // Mock para el repositorio
        when(categoriaRepository.findAll()).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> categoriaAdapter.getAllCategoriesOut());
    }

    @Test
    void getCategoryByIdOutSuccess() throws Exception {
        // Mock para el repositorio
        CategoriaEntity categoriaEntityEsperada = getMockCategoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaEntityEsperada));

        // Ejecutar el método bajo prueba
        CategoriaDTO categoriaDTORecibida = categoriaAdapter.getCategoryByIdOut(1L);

        // Verificar resultados
        assertEquals(categoriaEntityEsperada.getId_categoria(), categoriaDTORecibida.getIdCategoria());
        assertEquals(categoriaEntityEsperada.getEstado(), categoriaDTORecibida.getEstado());
        assertEquals(categoriaEntityEsperada.getDescripcion(), categoriaDTORecibida.getDescripcion());
    }

    @Test
    void getCategoryByIdOutError() {
        // Mock para el repositorio
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> categoriaAdapter.getCategoryByIdOut(1L));
    }

    @Test
    void updateCategoryByIdOutSuccess() {
        // Mock para requestCategoria
        RequestCategoria requestCategoria = new RequestCategoria("Ropa", 1);
        CategoriaEntity categoriaEntityEsperada = getMockCategoria();

        // Configurar comportamiento del repositorio
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaEntityEsperada));
        when(categoriaRepository.save(any(CategoriaEntity.class))).thenReturn(categoriaEntityEsperada);

        // Ejecutar el método bajo prueba
        CategoriaDTO categoriaDTORecibida = categoriaAdapter.updateCategoryByIdOut(1L, requestCategoria);

        // Verificar resultados
        assertEquals(categoriaEntityEsperada.getId_categoria(), categoriaDTORecibida.getIdCategoria());
        assertEquals(categoriaEntityEsperada.getEstado(), categoriaDTORecibida.getEstado());
        assertEquals(categoriaEntityEsperada.getDescripcion(), categoriaDTORecibida.getDescripcion());
    }

    @Test
    void updateCategoryByIdOutError() {
        // Mock para requestCategoria
        RequestCategoria requestCategoria = new RequestCategoria("Ropa", 1);

        // Configurar comportamiento del repositorio
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> categoriaAdapter.updateCategoryByIdOut(1L, requestCategoria));
    }

    @Test
    void deleteCategoryByIdOutSuccess() throws Exception {
        // Mock para el repositorio
        CategoriaEntity categoriaEntityEsperada = getMockCategoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaEntityEsperada));
        when(categoriaRepository.existsById(1L)).thenReturn(true);
        when(categoriaRepository.save(any(CategoriaEntity.class))).thenReturn(categoriaEntityEsperada);

        // Ejecutar el método bajo prueba
        CategoriaDTO categoriaDTORecibida = categoriaAdapter.deleteCategoryByIdOut(1L);

        // Verificar resultados
        assertEquals(categoriaEntityEsperada.getId_categoria(), categoriaDTORecibida.getIdCategoria());
        assertEquals(categoriaEntityEsperada.getEstado(), categoriaDTORecibida.getEstado());
        assertEquals(categoriaEntityEsperada.getDescripcion(), categoriaDTORecibida.getDescripcion());
    }

    @Test
    void deleteCategoryByIdOutError() {
        // Mock para el repositorio
        when(categoriaRepository.existsById(1L)).thenReturn(false);

        // Verificar resultados
        assertThrows(Exception.class, () -> categoriaAdapter.deleteCategoryByIdOut(1L));
    }

    private List<CategoriaEntity> getMockCategoriaList() {
        List<CategoriaEntity> categoriaEntities = new ArrayList<>();
        categoriaEntities.add(getMockCategoria());
        categoriaEntities.add(getMockCategoria());
        return categoriaEntities;
    }

    private CategoriaEntity getMockCategoria() {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId_categoria(1L);
        categoriaEntity.setDescripcion("Ropa");
        categoriaEntity.setEstado(1);
        return categoriaEntity;
    }
}