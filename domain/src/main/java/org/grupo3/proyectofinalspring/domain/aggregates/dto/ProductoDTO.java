package org.grupo3.proyectofinalspring.domain.aggregates.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO {
    private Long idProducto;
    private String descLargaProd;
    private String descCortaProd;
    private String empresa;
    private String marca;
    private String modelo;
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
