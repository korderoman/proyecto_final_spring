package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestPedido {
    private Long idPedido;
    private Integer estado;
}
