package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class RequestProducto {
    private String descLargaProd;
    private String descCortaProd;
    private String empresa;
    private String marca;
    private String modelo;
    private Timestamp fecIniVlg;
    private Timestamp fecFinVlg;
    private Integer estado;
    private Double precio;
    private Long categoria;
    private List<Long> caracteristicas;
}
