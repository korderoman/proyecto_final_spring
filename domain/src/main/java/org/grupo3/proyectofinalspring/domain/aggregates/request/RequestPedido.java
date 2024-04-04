package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RequestPedido {
    private Long idCliente;
    private Integer estado;
    private List<Integer> idsProductos; //[50,36,200,255]
    private List<Integer> cantidadesProductos; //[50,36,200,255]
}
