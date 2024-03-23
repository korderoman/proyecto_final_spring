package org.grupo3.proyectofinalspring.application.controller;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("empresa/productos")
@RequiredArgsConstructor
public class ProductController {
    @GetMapping("/all")
    public ResponseEntity<ProductoDTO> getAllProducts(){
        return  ResponseEntity.ok(null);
    }
}
