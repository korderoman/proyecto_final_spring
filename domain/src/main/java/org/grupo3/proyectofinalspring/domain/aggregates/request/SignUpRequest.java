package org.grupo3.proyectofinalspring.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SignUpRequest {
    private String dni;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String calle;
    private String numero;
    private String piso;
    private String codigPostal;
    private String telefonoCont;
    private String email;
    private String nomUsuario;
    private String password;
}
