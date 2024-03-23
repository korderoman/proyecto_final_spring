package org.grupo3.proyectofinalspring.application.controller;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;
import org.grupo3.proyectofinalspring.domain.ports.in.CaracteristicasServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("empresa/caracteristicas")
@RequiredArgsConstructor
public class CaracteristicasController {
    private final CaracteristicasServiceIn caracteristicasServiceIn;
    @PostMapping("/create")
    public ResponseEntity<CaracteristicasDTO> crearCaracteristica(@RequestBody RequestCaracteristicas requestCaracteristicas){
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicasServiceIn.addCaracteristicsIn(requestCaracteristicas));
    }
}
