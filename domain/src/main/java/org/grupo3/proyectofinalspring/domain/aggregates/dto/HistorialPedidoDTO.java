package org.grupo3.proyectofinalspring.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistorialPedidoDTO {
    private Long idHistPedido;
    private Timestamp fechaInicioPedido;
    private ClienteDTO clienteDTO;
    private PedidoDTO pedidoDTO;
}
