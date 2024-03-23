package org.grupo3.proyectofinalspring.domain.impl;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.domain.ports.in.ProductoServiceIn;

import java.util.List;
import java.util.Optional;

public class ProductoImpl implements ProductoServiceIn {
    @Override
    public ProductoDTO addProductIn(RequestProducto requestProducto) {
        return null;
    }

    @Override
    public List<ProductoDTO> getAllProductsIn() {
        return null;
    }

    @Override
    public Optional<ProductoDTO> getProductByIdIn(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductoDTO updateProductByIdIn(Long id, RequestProducto requestProducto) {
        return null;
    }

    @Override
    public ProductoDTO deleteProductByIdIn(Long id) {
        return null;
    }
}
