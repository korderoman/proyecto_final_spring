package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.domain.ports.on.ProductoServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.CategoriaEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CaracteristicasRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.CategoriaRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PrecioRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoAdapter implements ProductoServiceOut {
    private final PrecioRepository precioRepository;
    private final CategoriaRepository categoriaRepository;
    private final CaracteristicasRepository caracteristicasRepository;
    @Override
    public ProductoDTO addProductOut(RequestProducto requestProducto) {
        return null;
    }

    @Override
    public List<ProductoDTO> getAllProductsOut() {
        return null;
    }

    @Override
    public Optional<ProductoDTO> getProductByIdOut(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductoDTO updateProductByIdOut(Long id, RequestProducto requestProducto) {
        return null;
    }

    @Override
    public ProductoDTO deleteProductByIdOut(Long id) {
        return null;
    }


    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
