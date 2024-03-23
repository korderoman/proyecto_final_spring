package org.grupo3.proyectofinalspring.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.grupo3.proyectofinalspring.seguridad.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @GetMapping
    public ResponseEntity<String> saludoAdmin(){
        return ResponseEntity.ok("Hola Admin");
    }

    @PostMapping("/authentication/signup")
    public ResponseEntity<UsuarioDTO> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUpCliente(signUpRequest);
    }
    @PostMapping("/authentication/signin")
    public ResponseEntity<AuthenticationResponse> signin(@Valid @RequestBody SignInRequest signInRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
}
