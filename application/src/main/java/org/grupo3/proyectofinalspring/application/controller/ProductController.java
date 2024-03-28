package org.grupo3.proyectofinalspring.application.controller;

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
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody RequestProducto requestProducto){
        try {
            return  ResponseEntity.status(HttpStatus.CREATED).body(productoServiceIn.addProductIn(requestProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }


    @GetMapping("/all")
    public  ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos(){
        return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.getAllProductsIn());
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.getProductByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestProducto requestProducto){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(productoServiceIn.updateProductByIdIn(id,requestProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));

        }

    }

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
