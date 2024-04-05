package org.grupo3.proyectofinalspring.domain.aggregates.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestCategoria {
     private String descripcion;
    private Integer estado;
}
