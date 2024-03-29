package org.grupo3.proyectofinalspring.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Crea una nueva categoría",
            description = "Retorna un ResponseEntity con la nueva categoría creada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La categoría ha sido creada satisfactoriamente"),
    })
    @PostMapping("/create")
    public ResponseEntity<CategoriaDTO> createCategory(@RequestBody RequestCategoria requestCategoria){
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoriaServiceIn.addCategoryIn(requestCategoria));
    }

    @Operation(
            summary = "Obtiene todas las categorías",
            description = "Retorna un ResponseEntity con la lista de categorías, esta puede ser vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las categorías se obtuvieron satisfactoriamente"),
    })
    @GetMapping("/all")
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.getAllCategoriesIn());
    }

    @Operation(
            summary = "Busca una categoría por su id",
            description = "Retorna un ResponseEntity con la categoría solicitada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La categoría ha sido encontrada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La categoría no ha podido ser encontrada"),
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.getCategoryByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }

    @Operation(
            summary = "Actualiza una categoría por su id",
            description = "Retorna un ResponseEntity con la categoría actualizada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La categoría ha sido actualizada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La categoría no ha podido ser encontrada ni actualizada"),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestCategoria requestCategoria){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(categoriaServiceIn.updateCategoryByIdIn(id,requestCategoria));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }
    }


    @Operation(
            summary = "Elimina una categoría de forma lógica",
            description = "Retorna un ResponseEntity con la categoría eliminada lógicamente al cambiar su estado a 0"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La categoría ha sido eliminada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La categoría no ha podido ser eliminada"),
    })
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
