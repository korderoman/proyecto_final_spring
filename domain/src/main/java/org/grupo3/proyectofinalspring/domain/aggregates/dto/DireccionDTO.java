package org.grupo3.proyectofinalspring.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DireccionDTO {
    private Long idDireccion;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String calle;
    private String numero;
    private String piso;
    private String codigPostal;
    private String telefonoCont;
    private String email;

}
