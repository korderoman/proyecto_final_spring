package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceOut {
    ProductoDTO addProductOut(RequestProducto requestProducto) throws Exception;
    List<ProductoDTO> getAllProductsOut();
    ProductoDTO getProductByIdOut(Long id) throws Exception;
    ProductoDTO updateProductByIdOut(Long id, RequestProducto requestProducto) throws Exception;
    ProductoDTO deleteProductByIdOut(Long id) throws Exception;
}
