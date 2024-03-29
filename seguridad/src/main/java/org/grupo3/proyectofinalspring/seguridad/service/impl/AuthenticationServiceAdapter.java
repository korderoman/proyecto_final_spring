package org.grupo3.proyectofinalspring.seguridad.service.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.DireccionEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.RolEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.grupo3.proyectofinalspring.infraestructure.excepcions.ProcessException;
import org.grupo3.proyectofinalspring.infraestructure.mapper.UsuarioMapper;
import org.grupo3.proyectofinalspring.infraestructure.repository.*;
import org.grupo3.proyectofinalspring.seguridad.service.AuthenticationService;
import org.grupo3.proyectofinalspring.seguridad.service.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceAdapter implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    @Transactional //para que maneje los rollbacks ya que estamos insertando datos de muchas entidades.
    @Override
    public ResponseEntity<UsuarioDTO> signUpCliente(SignUpRequest signUpRequest) {
        //direccion
        DireccionEntity direccionEntity = direccionRepository.save(DireccionEntity.builder()
                .direccion(signUpRequest.getDireccion())
                .calle(signUpRequest.getCalle())
                .ciudad(signUpRequest.getCiudad())
                .provincia(signUpRequest.getProvincia())
                .numero(signUpRequest.getNumero())
                .piso(signUpRequest.getPiso())
                .codigPostal(signUpRequest.getCodigPostal())
                .telefonoCont(signUpRequest.getTelefonoCont())
                .email(signUpRequest.getEmail())
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario())
                .build());
        Optional <DireccionEntity> direccionGuardada= direccionRepository.findById(direccionEntity.getIdDireccion());
        if(direccionGuardada.isEmpty()) throw new ProcessException("La dirección no se pudo guardar en la base de datos.");
        //cliente
        ClienteEntity clienteEntity = clienteRepository.save(ClienteEntity.builder()
                .nombres(signUpRequest.getNombres())
                .dni(signUpRequest.getDni())
                .apePaterno(signUpRequest.getApePaterno())
                .apeMaterno(signUpRequest.getApeMaterno())
                .direccionEntity(direccionGuardada.get())
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario()).build());
        Optional <ClienteEntity> clienteGuardado= clienteRepository.findById(clienteEntity.getIdCliente());
        if(clienteGuardado.isEmpty()) throw new ProcessException("El cliente no se pudo guardar en la base de datos.");
        //roles
        Set<RolEntity> rolEntitySet = new HashSet<>();
        Optional <RolEntity> rol  = rolRepository.findByNombreRol("USER");
        if(rol.isEmpty()) throw new ProcessException("El rol no se encontró en la base de datos.");
        rolEntitySet.add(rol.get());
        //usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.mapUsuarioToDto(usuarioRepository.save(UsuarioEntity.builder()
                .clienteEntity(clienteEntity)
                .nomUsuario(signUpRequest.getNomUsuario())
                .password(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()))
                .roles(rolEntitySet)
                .clienteEntity(clienteEntity)
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario())
                .dateCreate(new Timestamp(System.currentTimeMillis()))
                .enabled(true)
                .accountnonexpire(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .build())));
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),signInRequest.getPassword()));
        try {
            AuthenticationResponse jwt = new AuthenticationResponse();
            Optional<UsuarioEntity> user = usuarioRepository.findByNomUsuario(signInRequest.getUsername());
            if (user.isEmpty()) {
                jwt.setToken("User not registered");
                return jwt;
            }
            // verificar la contraseña
            if (verifyPassword(signInRequest.getPassword(), user.get().getPassword())) {
                jwt.setToken(jwtService.generateToken(user.get()));
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
