package org.grupo3.proyectofinalspring.infraestructure.adapter;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.infraestructure.entity.CategoriaEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.CaracteristicasEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.ProductoEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CategoriaRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.CaracteristicasRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.ProductoRepository;
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
import static org.mockito.Mockito.*;

class ProductoAdapterTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private CaracteristicasRepository caracteristicasRepository;
    @Mock
    private ProductoRepository productoRepository;
    @InjectMocks
    private ProductoAdapter productoAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addProductOut_Success() throws Exception {
        // Mock para requestProducto
        RequestProducto requestProducto = new RequestProducto();
        requestProducto.setCategoria(1L);
        requestProducto.setDescCortaProd("Corta descripción");
        requestProducto.setDescLargaProd("Larga descripción");
        requestProducto.setEmpresa("Empresa");
        requestProducto.setMarca("Marca");
        requestProducto.setModelo("Modelo");
        requestProducto.setPrecio(100.0);
        requestProducto.setEstado(1);
        requestProducto.setCaracteristicas(List.of(1L, 2L));

        // Mock para entidad de producto
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId_categoria(1L); // Ajusta este ID según tu configuración
        CaracteristicasEntity caracteristicasEntity1 = new CaracteristicasEntity();
        caracteristicasEntity1.setId_caracteristicas(1L); // Ajusta este ID según tu configuración
        CaracteristicasEntity caracteristicasEntity2 = new CaracteristicasEntity();
        caracteristicasEntity2.setId_caracteristicas(2L); // Ajusta este ID según tu configuración

        ProductoEntity productoEntityEsperada = getProductoEntity();
        // Ajusta otros atributos de acuerdo a tu configuración

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaEntity));
        when(caracteristicasRepository.findById(1L)).thenReturn(Optional.of(caracteristicasEntity1));
        when(caracteristicasRepository.findById(2L)).thenReturn(Optional.of(caracteristicasEntity2));
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntityEsperada);

        // Ejecutar el método bajo prueba
        ProductoDTO productoDTORecibida = productoAdapter.addProductOut(requestProducto);

        assertEquals(1L, productoDTORecibida.getIdProducto());
        assertEquals("Corta descripción", productoDTORecibida.getDescCortaProd());
        assertEquals("Larga descripción", productoDTORecibida.getDescLargaProd());
        assertEquals("Empresa", productoDTORecibida.getEmpresa());
        assertEquals("Marca", productoDTORecibida.getMarca());
        assertEquals("Modelo", productoDTORecibida.getModelo());
        assertEquals(100.0, productoDTORecibida.getPrecio());
        assertEquals(1, productoDTORecibida.getEstado());

        // Verificar que se llama al método findById del repositorio de categorías exactamente una vez con el ID correcto
        verify(categoriaRepository, times(1)).findById(1L);

        // Verificar que se llama al método findById del repositorio de características exactamente dos veces con los IDs correctos
        verify(caracteristicasRepository, times(1)).findById(1L);
        verify(caracteristicasRepository, times(1)).findById(2L);

        // Verificar que se llama al método save del repositorio de productos exactamente una vez con una instancia de ProductoEntity
        verify(productoRepository, times(1)).save(any(ProductoEntity.class));
    }

    @Test
    void addProductOut_Error() {
        // Mock para requestProducto
        RequestProducto requestProducto = new RequestProducto();

        // Verificar resultados
        assertThrows(Exception.class, () -> productoAdapter.addProductOut(requestProducto));
    }

    @Test
    void getAllProductsOut_Success() {
        List<ProductoEntity> entities = new ArrayList<>();
        // Add your desired number of PersonaEntity objects with necessary fields set
        entities.add(getProductoEntity());
        List<ProductoDTO> expectedDTOs = new ArrayList<>();
        for (ProductoEntity entity : entities) {
            expectedDTOs.add(ProductoDTO.builder().idProducto(1L).build()); // Set expected DTO fields based on entity
        }

        when(productoRepository.findAll()).thenReturn(entities);
        //when(setProductoEntityToProductoDto(entities.get(0))).thenReturn(expectedDTOs.get(0));

        List<ProductoDTO> actualDTOs = productoAdapter.getAllProductsOut();

        assertEquals(expectedDTOs.size(), actualDTOs.size());
        for (int i = 0; i < expectedDTOs.size(); i++) {
            assertEquals(expectedDTOs.get(i).getIdProducto(), actualDTOs.get(i).getIdProducto());
        }
    }

    @Test
    void getAllProductsOut_Error() {
        // Mock para el repositorio
        when(productoRepository.findAll()).thenThrow(RuntimeException.class);

        // Verificar resultados
        assertThrows(Exception.class, () -> productoAdapter.getAllProductsOut());
    }

    @Test
    void getProductByIdOut_Success() throws Exception {
        // Mock para el repositorio
        ProductoEntity productoEntityEsperada = getProductoEntity();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEntityEsperada));

        // Ejecutar el método bajo prueba
        ProductoDTO productoDTORecibida = productoAdapter.getProductByIdOut(1L);

        // Verificar resultados
        assertNotNull(productoDTORecibida);
        assertEquals(productoEntityEsperada.getId_producto(), productoDTORecibida.getIdProducto());
    }

    @Test
    void getProductByIdOut_Error() {
        // Mock para el repositorio
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar resultados
        assertThrows(Exception.class, () -> productoAdapter.getProductByIdOut(1L));
    }

    @Test
    void updateProductByIdOut_Success() throws Exception {
        // Mock para requestProducto
        RequestProducto requestProducto = new RequestProducto();
        requestProducto.setDescCortaProd("Nueva corta descripción");
        requestProducto.setDescLargaProd("Nueva larga descripción");
        requestProducto.setEmpresa("Nueva empresa");
        requestProducto.setMarca("Nueva marca");
        requestProducto.setModelo("Nuevo modelo");
        requestProducto.setPrecio(200.0);
        requestProducto.setEstado(1);
        requestProducto.setCaracteristicas(List.of(3L, 4L));

        // Mock para el repositorio
        ProductoEntity productoEntityEsperada = getProductoEntity();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEntityEsperada));
        when(caracteristicasRepository.findById(any())).thenReturn(Optional.of(new CaracteristicasEntity()));
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntityEsperada);

        // Ejecutar el método bajo prueba
        ProductoDTO productoDTORecibida = productoAdapter.updateProductByIdOut(1L, requestProducto);

        // Verificar resultados
        assertNotNull(productoDTORecibida);
        assertEquals(productoEntityEsperada.getId_producto(), productoDTORecibida.getIdProducto());
    }

    @Test
    void updateProductByIdOut_Error() {
        // Mock para requestProducto
        RequestProducto requestProducto = new RequestProducto();

        // Verificar resultados
        assertThrows(Exception.class, () -> productoAdapter.updateProductByIdOut(1L, requestProducto));
    }

    @Test
    void deleteProductByIdOut_Success() throws Exception {
        // Mock para el repositorio
        ProductoEntity productoEntityEsperada = getProductoEntity();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEntityEsperada));
        when(productoRepository.existsById(1L)).thenReturn(true);
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntityEsperada);

        // Ejecutar el método bajo prueba
        ProductoDTO productoDTORecibida = productoAdapter.deleteProductByIdOut(1L);

        // Verificar resultados
        assertNotNull(productoDTORecibida);
        assertEquals(productoEntityEsperada.getId_producto(), productoDTORecibida.getIdProducto());
    }

    @Test
    void deleteProductByIdOut_Error() {
        // Mock para el repositorio
        when(productoRepository.existsById(1L)).thenReturn(false);

        // Verificar resultados
        assertThrows(Exception.class, () -> productoAdapter.deleteProductByIdOut(1L));
    }

    private ProductoEntity getProductoEntity() {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setId_producto(1L);
        productoEntity.setDesc_larga_prod("Larga descripción");
        productoEntity.setDesc_corta_prod("Corta descripción");
        productoEntity.setEmpresa("Empresa");
        productoEntity.setMarca("Marca");
        productoEntity.setModelo("Modelo");
        productoEntity.setEstado(1);
        productoEntity.setPrecio(100.0); // Precio del producto
        productoEntity.setUsuaCrea("Usuario Creador");

        // Configurar características del producto
        List<CaracteristicasEntity> caracteristicasEntities = new ArrayList<>();
        CaracteristicasEntity caracteristicasEntity1 = new CaracteristicasEntity();
        caracteristicasEntity1.setId_caracteristicas(1L);
        caracteristicasEntity1.setDescripcion("Característica 1");
        caracteristicasEntity1.setEstado(1);
        caracteristicasEntities.add(caracteristicasEntity1);

        CaracteristicasEntity caracteristicasEntity2 = new CaracteristicasEntity();
        caracteristicasEntity2.setId_caracteristicas(2L);
        caracteristicasEntity2.setDescripcion("Característica 2");
        caracteristicasEntity2.setEstado(1);
        caracteristicasEntities.add(caracteristicasEntity2);

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setDescripcion("lolo");
        categoriaEntity.setProductos(new ArrayList<>());

        productoEntity.setCaracteristicas(caracteristicasEntities);
        productoEntity.setCategoria(categoriaEntity);
        // También necesitas configurar la categoría del producto

        return productoEntity;
    }
}