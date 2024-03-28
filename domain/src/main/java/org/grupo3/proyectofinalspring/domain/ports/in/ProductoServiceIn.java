package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceIn {
    ProductoDTO addProductIn(RequestProducto requestProducto) throws Exception;
    List<ProductoDTO> getAllProductsIn();
    ProductoDTO getProductByIdIn(Long id) throws Exception;
    ProductoDTO updateProductByIdIn(Long id, RequestProducto requestProducto) throws Exception;
    ProductoDTO deleteProductByIdIn(Long id) throws Exception;
}
