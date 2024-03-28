package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaServiceOut {
    CategoriaDTO addCategoryOut(RequestCategoria requestCategoria);
    List<CategoriaDTO> getAllCategoriesOut();
    CategoriaDTO getCategoryByIdOut(Long id) throws Exception;
    CategoriaDTO updateCategoryByIdOut(Long id, RequestCategoria requestCategoria);
    CategoriaDTO deleteCategoryByIdOut(Long id) throws Exception;
}
