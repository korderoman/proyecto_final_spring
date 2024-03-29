package org.grupo3.proyectofinalspring.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Crear una nueva característica",
            description = "Retorna un ResponseEntity con la característica creada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La característica ha sido creada satisfactoriamente"),
    })
    @PostMapping("/create")
    public ResponseEntity<CaracteristicasDTO> crearCaracteristica(@RequestBody RequestCaracteristicas requestCaracteristicas){
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicasServiceIn.addCaracteristicsIn(requestCaracteristicas));
    }


    @Operation(
            summary = "Retorna todas las características",
            description = "Retorna un ResponseEntity con la lista de características, esta puede ser una lista vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las características han sido retornadas satisfactoriamente"),
    })
    @GetMapping("/all")
    public ResponseEntity<List<CaracteristicasDTO>> obtenerTodasLasCaracteristicas(){
        return ResponseEntity.status(HttpStatus.OK).body(caracteristicasServiceIn.getAllCaracteristicsIn());
    }

    @Operation(
            summary = "Busca una característica por su id",
            description = "Retorna un ResponseEntity con la característica solicitada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La característica ha sido encontrada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La característica no ha podido ser encontrada"),
    })
    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(caracteristicasServiceIn.getCaracteristicsByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }

    }

    @Operation(
            summary = "Actualiza una característica por su id",
            description = "Retorna un ResponseEntity con la característica actualizada o en su defecto creada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La característica ha sido actualizada satisfactoriamente"),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestCaracteristicas requestCaracteristicas){
        CaracteristicasDTO caracteristicasDTO = caracteristicasServiceIn.updateCaracteristicsByIdIn(id, requestCaracteristicas);
        return  ResponseEntity.status(HttpStatus.OK).body(caracteristicasDTO);

    }

    @Operation(
            summary = "Elimina característica  de forma lógica por su estado (0)",
            description = "Retorna un ResponseEntity con la característica eliminada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La característica ha sido eliminada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La característica no ha podido ser eliminada"),
    })
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
