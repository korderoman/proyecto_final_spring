package org.grupo3.proyectofinalspring.seguridad.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
