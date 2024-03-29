package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RequestFactura {
    private String numFactura;
    private int cantidad;
    private double igv;
    private Timestamp fechaIngreso;
}
