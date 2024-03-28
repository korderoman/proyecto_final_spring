package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.domain.ports.in.ProductoServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.ProductoServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoImpl implements ProductoServiceIn {

    private final ProductoServiceOut productoServiceOut;
    @Override
    public ProductoDTO addProductIn(RequestProducto requestProducto) throws Exception
    {
        return productoServiceOut.addProductOut(requestProducto);
    }

    @Override
    public List<ProductoDTO> getAllProductsIn() {
        return productoServiceOut.getAllProductsOut();
    }

    @Override
    public ProductoDTO getProductByIdIn(Long id) throws Exception {
        return  productoServiceOut.getProductByIdOut(id);
    }

    @Override
    public ProductoDTO updateProductByIdIn(Long id, RequestProducto requestProducto) throws Exception {
        return productoServiceOut.updateProductByIdOut(id,requestProducto);
    }

    @Override
    public ProductoDTO deleteProductByIdIn(Long id) throws Exception {
        return  productoServiceOut.deleteProductByIdOut(id);
    }
}
