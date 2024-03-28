package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;
import org.grupo3.proyectofinalspring.domain.ports.in.CategoriaServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.CategoriaServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaImpl implements CategoriaServiceIn {
    private final CategoriaServiceOut categoriaServiceOut;
    @Override
    public CategoriaDTO addCategoryIn(RequestCategoria requestCategoria) {
        return categoriaServiceOut.addCategoryOut(requestCategoria);
    }

    @Override
    public List<CategoriaDTO> getAllCategoriesIn() {
        return categoriaServiceOut.getAllCategoriesOut();
    }

    @Override
    public CategoriaDTO getCategoryByIdIn(Long id) throws  Exception {
        return categoriaServiceOut.getCategoryByIdOut(id);
    }

    @Override
    public CategoriaDTO updateCategoryByIdIn(Long id, RequestCategoria requestCategoria) {
        return categoriaServiceOut.updateCategoryByIdOut(id,requestCategoria);
    }

    @Override
    public CategoriaDTO deleteCategoryByIdIn(Long id) throws Exception {
        return categoriaServiceOut.deleteCategoryByIdOut(id);
    }
}
