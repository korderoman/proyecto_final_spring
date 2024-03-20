package org.grupo3.proyectofinalspring.domain.aggregates.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecioDTO {
    private Long idPrecio;
    private double precio;
    private boolean oferta;
    private Timestamp fecIniVlg;
    private Timestamp fecFinVlg;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
