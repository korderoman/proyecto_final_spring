package org.grupo3.proyectofinalspring.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;
import org.grupo3.proyectofinalspring.domain.ports.in.FacturaServiceIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empresa/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaServiceIn facturaServiceIn;
    @Operation(
            summary = "Crea una nueva factura",
            description = "Retorna un ResponseEntity con la nueva factura creada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La factura ha sido creada satisfactoriamente"),
    })
    @PostMapping("/create")
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody RequestFactura requestFactura){
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaServiceIn.addFacturaIn(requestFactura));
    }
    @Operation(
            summary = "Obtiene todas las facturas",
            description = "Retorna un ResponseEntity con la lista de facturas, esta puede ser vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las facturas se obtuvieron satisfactoriamente"),
    })
    @GetMapping("/all")
    public ResponseEntity<List<FacturaDTO>> obtenerTodasLasFacturas(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaServiceIn.getAllFacturaIn());
    }

    @Operation(
            summary = "Busca una factura por su id",
            description = "Retorna un ResponseEntity con la categoría solicitada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La factura ha sido encontrada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La factura no ha podido ser encontrada"),
    })
    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(facturaServiceIn.getFacturaByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }

    }


    @Operation(
            summary = "Actualiza una factura por su id",
            description = "Retorna un ResponseEntity con la factura actualizada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La factura ha sido actualizada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La factura no ha podido ser encontrada ni actualizada"),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestFactura requestFactura){
        FacturaDTO facturaDTO = facturaServiceIn.updateFacturaByIdIn(id, requestFactura);
        return  ResponseEntity.status(HttpStatus.OK).body(facturaDTO);

    }

    @Operation(
            summary = "Elimina una factura de forma lógica",
            description = "Retorna un ResponseEntity con la factura eliminada lógicamente al cambiar su estado a 0"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La factura ha sido eliminada satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "La factura no ha podido ser eliminada"),
    })
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(facturaServiceIn.deleteFacturaByIdIn(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }

    private String solveCheckError(Exception e){
        return e.getMessage();
    }
}
