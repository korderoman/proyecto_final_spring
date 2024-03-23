package org.grupo3.proyectofinalspring.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private Long idCliente;
    private String dni;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private DireccionDTO direccionDTO;
}
