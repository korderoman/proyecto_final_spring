package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceIn {
    ProductoDTO addProductIn(RequestProducto requestProducto) throws Exception;
    List<ProductoDTO> getAllProductsIn();
    Optional<ProductoDTO> getProductByIdIn(Long id);
    ProductoDTO updateProductByIdIn(Long id, RequestProducto requestProducto);
    ProductoDTO deleteProductByIdIn(Long id);
}
