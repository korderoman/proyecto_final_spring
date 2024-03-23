package org.grupo3.proyectofinalspring.application.controller;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;
import org.grupo3.proyectofinalspring.domain.ports.in.CategoriaServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("empresa/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaServiceIn categoriaServiceIn;

    @PostMapping("/create")
    public ResponseEntity<CategoriaDTO> createCategory(@RequestBody RequestCategoria requestCategoria){
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoriaServiceIn.addCategoryIn(requestCategoria));
    }
}
