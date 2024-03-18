package org.grupo3.proyectofinalspring.seguridad.service.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.UsuarioRepository;
import org.grupo3.proyectofinalspring.seguridad.request.SignInRequest;
import org.grupo3.proyectofinalspring.seguridad.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.seguridad.service.AuthenticationService;
import org.grupo3.proyectofinalspring.seguridad.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),signInRequest.getPassword()));
        try {
            AuthenticationResponse jwt = new AuthenticationResponse();
            Optional<UsuarioEntity> user = usuarioRepository.findByUsername(signInRequest.getUsername());
            if (user.isEmpty()) {
                jwt.setToken("User not registered");
                return jwt;
            }
            // verificar la contraseña
            if (verifyPassword(signInRequest.getPassword(), user.get().getPassword())) {
                jwt.setToken(jwtService.generateToken((UserDetails) user.get()));
            } else{
                jwt.setToken("Authentication failed");
            }
            return jwt;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword); // si hace match true, si no false
    }
}
