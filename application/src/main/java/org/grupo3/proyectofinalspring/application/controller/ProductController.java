package org.grupo3.proyectofinalspring.application.controller;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.domain.ports.in.ProductoServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresa/productos")
@RequiredArgsConstructor
public class ProductController {
    private final ProductoServiceIn productoServiceIn;
    @PostMapping("/create")
    public ResponseEntity<?> getAllProducts(@RequestBody RequestProducto requestProducto){
        try {
            return  ResponseEntity.status(HttpStatus.CREATED).body(productoServiceIn.addProductIn(requestProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }


    private String solveCheckError(Exception e){
        System.out.println(e.getMessage());
        return "Algo no salio bien";
    }
}
