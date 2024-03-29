package org.grupo3.proyectofinalspring.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Crea un nuevo pedido",
            description = "Retorna un ResponseEntity con el nuevo pedido creado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El pedido ha sido creada satisfactoriamente"),
    })
    @PostMapping("/crear")
    public ResponseEntity<PedidoDTO> registrarPedido(@RequestBody RequestPedido requestPedido){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoServiceIn.CrearPedidoIn(requestPedido));
    }

    @Operation(
            summary = "Obtiene todos los pedidos",
            description = "Retorna un ResponseEntity con la lista de pedidos, esta puede ser vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Los pedidos se obtuvieron satisfactoriamente"),
    })
    @GetMapping("/all")
    public ResponseEntity<List<PedidoDTO>> obtenerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceIn.ObtenerTodosPedidosIn());
    }

    @Operation(
            summary = "Busca un pedido por su id",
            description = "Retorna un ResponseEntity con el pedido solicitado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El pedido ha sido encontrado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El pedido no ha podido ser encontrado"),
    })
    @GetMapping("/find/{id}")
    public  ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(pedidoServiceIn.BuscarPedidoPorIdIn(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(solveCheckError(e));
        }

    }

    @Operation(
            summary = "Actualiza un pedido por su id",
            description = "Retorna un ResponseEntity con el pedido actualizado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El pedido ha sido actualizado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El pedido no ha podido ser encontrado ni actualizado"),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarPorId(@PathVariable Long id, @RequestBody RequestPedido requestPedido){
        PedidoDTO pedidoDTO = pedidoServiceIn.ActualizarPedidoIn(id, requestPedido);
        return  ResponseEntity.status(HttpStatus.OK).body(pedidoDTO);

    }

    @Operation(
            summary = "Elimina un pedido de forma lógica",
            description = "Retorna un ResponseEntity con el pedido eliminado lógicamente al cambiar su estado a 0"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El pedido ha sido eliminado satisfactoriamente"),
            @ApiResponse(responseCode = "500", description = "El pedido no ha podido ser eliminado"),
    })
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

