package org.grupo3.proyectofinalspring.application.controller;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.PedidoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestPedido;
import org.grupo3.proyectofinalspring.domain.ports.in.PedidoServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente/pedidos")
public class PedidoController {
    @Autowired
    private PedidoServiceIn pedidoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<PedidoDTO> registrarPedido(@RequestBody RequestPedido requestPedido){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoServiceIn.CrearPedidoIn(requestPedido));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PedidoDTO>> obtenerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceIn.ObtenerTodosPedidosIn());
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(pedidoServiceIn.BuscarPedidoPorIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestPedido requestPedido){
        PedidoDTO pedidoDTO = pedidoServiceIn.ActualizarPedidoIn(id, requestPedido);
        return  ResponseEntity.status(HttpStatus.OK).body(pedidoDTO);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(pedidoServiceIn.EliminarPedidoIn(id));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }
    }

    private String solveCheckError(Exception e){
        return e.getMessage();
    }
}

