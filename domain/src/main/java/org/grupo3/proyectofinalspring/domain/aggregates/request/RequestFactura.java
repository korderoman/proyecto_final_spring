package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class RequestFactura {
    private Long idPedido;
    private String numFactura;
    private int cantidad;
    private double igv;
    private Timestamp fechaIngreso;
    private int estado;
}
