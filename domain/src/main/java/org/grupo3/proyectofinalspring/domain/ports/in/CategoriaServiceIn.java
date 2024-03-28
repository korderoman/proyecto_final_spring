package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaServiceIn {
    CategoriaDTO addCategoryIn(RequestCategoria requestCategoria);
    List<CategoriaDTO> getAllCategoriesIn();
    CategoriaDTO getCategoryByIdIn(Long id) throws Exception;
    CategoriaDTO updateCategoryByIdIn(Long id, RequestCategoria requestCategoria);
    CategoriaDTO deleteCategoryByIdIn(Long id) throws Exception;
}
