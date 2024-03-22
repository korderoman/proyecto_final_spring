package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceOut {
    ProductoDTO addProductOut(RequestProducto requestProducto);
    List<ProductoDTO> getAllProductsOut();
    Optional<ProductoDTO> getProductByIdOut(Long id);
    ProductoDTO updateProductByIdOut(Long id, RequestProducto requestProducto);
    ProductoDTO deleteProductByIdOut(Long id);
}
