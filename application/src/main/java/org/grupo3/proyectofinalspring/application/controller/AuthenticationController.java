package org.grupo3.proyectofinalspring.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.seguridad.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authentication/signup")
    public ResponseEntity<UsuarioDTO> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUpCliente(signUpRequest);
    }
    @PostMapping("/admin/authentication/signup")
    public ResponseEntity<UsuarioDTO> signUpAdmin(@Valid @RequestBody SignUpRequest signUpRequest){
        return authenticationService.signUpAdmin(signUpRequest);
    }
    @PostMapping("/authentication/signin")
    public ResponseEntity<AuthenticationResponse> signin(@Valid @RequestBody SignInRequest signInRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteEntity>> getAllClients(){
        return  authenticationService.listClientes();
    }
}
