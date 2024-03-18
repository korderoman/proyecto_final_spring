package org.grupo3.proyectofinalspring.seguridad.service;

import org.grupo3.proyectofinalspring.seguridad.request.SignInRequest;
import org.grupo3.proyectofinalspring.seguridad.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception;
}
