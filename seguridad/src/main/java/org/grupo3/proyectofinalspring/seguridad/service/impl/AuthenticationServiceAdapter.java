package org.grupo3.proyectofinalspring.seguridad.service.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.domain.aggregates.response.ResponseReniec;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.DireccionEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.RolEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.grupo3.proyectofinalspring.infraestructure.excepcions.ProcessException;
import org.grupo3.proyectofinalspring.infraestructure.mapper.UsuarioMapper;
import org.grupo3.proyectofinalspring.infraestructure.repository.*;
import org.grupo3.proyectofinalspring.infraestructure.rest.cliente.ClienteReniec;
import org.grupo3.proyectofinalspring.seguridad.service.AuthenticationService;
import org.grupo3.proyectofinalspring.seguridad.service.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final ClienteReniec clienteReniec;
    @Value("${token.api}")
    private String tokenApi;
    @Transactional //para que maneje los rollbacks ya que estamos insertando datos de muchas entidades.
    @Override
    public ResponseEntity<UsuarioDTO> signUpCliente(SignUpRequest signUpRequest) {
        //direccion
        Optional<DireccionEntity> direccionGuardada = registerDireccion(signUpRequest);
        //cliente
        Optional<ClienteEntity> clienteGuardado = registerCLienteWithReniec(signUpRequest, direccionGuardada);
        //roles
        Set<RolEntity> rolEntitySet = new HashSet<>();
        Optional <RolEntity> rol  = rolRepository.findByNombreRol("USER");
        if(rol.isEmpty()) throw new ProcessException("El rol no se encontró en la base de datos.");
        rolEntitySet.add(rol.get());
        //usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUsuario(signUpRequest, clienteGuardado,rolEntitySet));
    }
    @Transactional
    @Override
    public ResponseEntity<UsuarioDTO> signUpAdmin(SignUpRequest signUpRequest) {
        //direccion
        Optional<DireccionEntity> direccionGuardada = registerDireccion(signUpRequest);
        //cliente
        Optional<ClienteEntity> clienteGuardado = registerCLienteWithReniec(signUpRequest, direccionGuardada);
        //roles
        Set<RolEntity> rolEntitySet = new HashSet<>();
        Optional <RolEntity> rol1  = rolRepository.findByNombreRol("USER");
        if(rol1.isEmpty()) throw new ProcessException("El rol USER no se encontró en la base de datos.");
        Optional <RolEntity> rol2 = rolRepository.findByNombreRol("ADMIN");
        if(rol2.isEmpty()) throw new ProcessException("El rol ADMIN no se encontró en la base de datos.");

        rolEntitySet.add(rol1.get());
        rolEntitySet.add(rol2.get());
        //usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUsuario(signUpRequest, clienteGuardado,rolEntitySet));
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),signInRequest.getPassword()));
        try {
            AuthenticationResponse jwt = new AuthenticationResponse();
            Optional<UsuarioEntity> user = usuarioRepository.findByNomUsuario(signInRequest.getUsername());
            if (user.isEmpty()) {
                jwt.setToken("Usuario no registrado");
                return jwt;
            }
            // verificar la contraseña
            if (verifyPassword(signInRequest.getPassword(), user.get().getPassword())) {
                jwt.setToken(jwtService.generateToken(user.get()));
            } else{
                jwt.setToken("la authenticación falló");
            }
            return jwt;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    private Optional<ClienteEntity> registerCLienteWithReniec(SignUpRequest signUpRequest, Optional<DireccionEntity> direccionGuardada){
        //reniec
        ResponseReniec responseReniec= getExecutionReniec(signUpRequest.getDni());
        //cliente
        ClienteEntity clienteEntity = clienteRepository.save(ClienteEntity.builder()
                .nombres(responseReniec.getNombres())
                .dni(signUpRequest.getDni())
                .apePaterno(responseReniec.getApellidoPaterno())
                .apeMaterno(responseReniec.getApellidoMaterno())
                .direccionEntity(direccionGuardada.get())
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario()).build());
        Optional<ClienteEntity> clienteGuardado= clienteRepository.findById(clienteEntity.getIdCliente());
        if(clienteGuardado.isEmpty()) throw new ProcessException("El cliente no se pudo guardar en la base de datos");
        return clienteGuardado;
    }
    private Optional<DireccionEntity> registerDireccion(SignUpRequest signUpRequest){
        //validar datos
        if(clienteRepository.existsByDni(signUpRequest.getDni())) throw new ProcessException("El DNI ya existe en la Base de datos");
        if(direccionRepository.existsByEmail(signUpRequest.getEmail())) throw new ProcessException("El email ya existe en la Base de datos");
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
        return direccionGuardada;
    }
    private UsuarioDTO registerUsuario(SignUpRequest signUpRequest, Optional<ClienteEntity> clienteGuardado, Set<RolEntity> rolEntitySet){
        if(usuarioRepository.existsByNomUsuario(signUpRequest.getNomUsuario())) throw new ProcessException("El nombre de usuario ya existe en la base de datos");
        return usuarioMapper.mapUsuarioToDto(usuarioRepository.save(UsuarioEntity.builder()
                .clienteEntity(clienteGuardado.get())
                .nomUsuario(signUpRequest.getNomUsuario())
                .password(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()))
                .roles(rolEntitySet)
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario())
                .dateCreate(new Timestamp(System.currentTimeMillis()))
                .enabled(true)
                .accountnonexpire(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .build()));
    }
    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
            return clienteReniec.getInfoReniec(numero,authorization);
    }
    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword); // si hace match true, si no false
    }
}
