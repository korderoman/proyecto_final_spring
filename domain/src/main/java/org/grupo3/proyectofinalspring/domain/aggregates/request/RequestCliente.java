package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCliente {
    private String numDoc;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String calle;
    private String numero;
    private String piso;
    private String codigo_postal;
    private String telefonoCont;
    private String email;
}
