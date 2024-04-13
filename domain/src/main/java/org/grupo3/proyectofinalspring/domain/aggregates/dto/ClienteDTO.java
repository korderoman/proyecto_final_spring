package org.grupo3.proyectofinalspring.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private Long idCliente;
    private String dni;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private DireccionDTO direccionDTO;
}
