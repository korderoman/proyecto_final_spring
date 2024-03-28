package org.grupo3.proyectofinalspring.application.controller;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;
import org.grupo3.proyectofinalspring.domain.ports.in.CaracteristicasServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("empresa/caracteristicas")
@RequiredArgsConstructor
public class CaracteristicasController {
    private final CaracteristicasServiceIn caracteristicasServiceIn;
    @PostMapping("/create")
    public ResponseEntity<CaracteristicasDTO> crearCaracteristica(@RequestBody RequestCaracteristicas requestCaracteristicas){
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicasServiceIn.addCaracteristicsIn(requestCaracteristicas));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CaracteristicasDTO>> obtenerTodasLasCaracteristicas(){
        return ResponseEntity.status(HttpStatus.OK).body(caracteristicasServiceIn.getAllCaracteristicsIn());
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(caracteristicasServiceIn.getCaracteristicsByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestCaracteristicas requestCaracteristicas){
        CaracteristicasDTO caracteristicasDTO = caracteristicasServiceIn.updateCaracteristicsByIdIn(id, requestCaracteristicas);
        return  ResponseEntity.status(HttpStatus.OK).body(caracteristicasDTO);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(caracteristicasServiceIn.deleteCaracteristicsByIdIn(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }

    private String solveCheckError(Exception e){
        return e.getMessage();
    }
}
