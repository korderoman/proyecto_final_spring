package org.grupo3.proyectofinalspring.seguridad.service;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthenticationService {
    ResponseEntity<UsuarioDTO> signUpCliente(SignUpRequest signUpRequest);
    ResponseEntity<UsuarioDTO> signUpAdmin(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception;
    ResponseEntity<List<ClienteEntity>> listClientes();
}
