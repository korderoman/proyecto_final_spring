package org.grupo3.proyectofinalspring.application.controller;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;
import org.grupo3.proyectofinalspring.domain.ports.in.CategoriaServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empresa/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaServiceIn categoriaServiceIn;

    @PostMapping("/create")
    public ResponseEntity<CategoriaDTO> createCategory(@RequestBody RequestCategoria requestCategoria){
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoriaServiceIn.addCategoryIn(requestCategoria));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.getAllCategoriesIn());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.getCategoryByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestCategoria requestCategoria){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.updateCategoryByIdIn(id,requestCategoria));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.deleteCategoryByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }
    }

    private String solveCheckError(Exception e){
        return e.getMessage();
    }
}
