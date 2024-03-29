package org.grupo3.proyectofinalspring.application.controller;
//https://www.baeldung.com/swagger-set-example-description
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.domain.ports.in.ProductoServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empresa/productos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductoServiceIn productoServiceIn;
    @Operation(
            summary = "Crea un nuevo producto",
            description = "Retorna un ResponseEntity que puede ser el producto creado o una respuesta de error"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El producto ha sido creado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El producto no ha podido ser creado debido a alguna de sus dependencias")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody RequestProducto requestProducto){
        try {
            return  ResponseEntity.status(HttpStatus.CREATED).body(productoServiceIn.addProductIn(requestProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(solveCheckError(e));
        }
    }


    @Operation(
            summary = "Retorna todos los productos producto",
            description = "Retorna una Lista de productos; puede ser vacía en caso de no encontrarse alguno"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Los producto han sido obtenidos satisfactoriamente"),

    })
    @GetMapping("/all")
    public  ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos(){
        return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.getAllProductsIn());
    }

    @Operation(
            summary = "Encuentra un producto por su id",
            description = "Retorna un ResponseEntity que puede ser el producto encontrado o una respuesta de error"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El producto ha sido encontrado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El producto no ha podido ser encontrado")
    })
    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.getProductByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }
    }
    @Operation(
            summary = "Actualiza un producto",
            description = "Retorna un ResponseEntity que puede ser el producto actualizado o una respuesta de error"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El producto ha sido actualizado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El producto no ha podido ser actualizado debido a alguna de sus dependencias")
    })
    @PutMapping("/update/{id}")
    public  ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestProducto requestProducto){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.updateProductByIdIn(id,requestProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }

    }

    @Operation(
            summary = "Elimina lógicamente  un producto",
            description = "Retorna un ResponseEntity que puede ser el producto con el estado cero o una respuesta de error"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El producto ha sido cambiado de estado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El producto no ha podido ser cambiado de estado porque no se ha encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.deleteProductByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }

    }


    private String solveCheckError(Exception e){
       return  e.getMessage();
    }
}
