package org.grupo3.proyectofinalspring.application.controller;

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
    @PostMapping("/create")
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody RequestFactura requestFactura){
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaServiceIn.addFacturaIn(requestFactura));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FacturaDTO>> obtenerTodasLasFacturas(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaServiceIn.getAllFacturaIn());
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(facturaServiceIn.getFacturaByIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestFactura requestFactura){
        FacturaDTO facturaDTO = facturaServiceIn.updateFacturaByIdIn(id, requestFactura);
        return  ResponseEntity.status(HttpStatus.OK).body(facturaDTO);

    }

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
