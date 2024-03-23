package org.grupo3.proyectofinalspring.seguridad.service;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<UsuarioDTO> signUpCliente(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception;
}
