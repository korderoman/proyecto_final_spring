package org.grupo3.proyectofinalspring.seguridad.service.impl;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ClienteDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.DireccionDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.RolDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignInRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.request.SignUpRequest;
import org.grupo3.proyectofinalspring.domain.aggregates.response.AuthenticationResponse;
import org.grupo3.proyectofinalspring.domain.aggregates.response.ResponseReniec;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.DireccionEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.RolEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.grupo3.proyectofinalspring.infraestructure.mapper.UsuarioMapper;
import org.grupo3.proyectofinalspring.infraestructure.repository.ClienteRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.DireccionRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.RolRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.UsuarioRepository;
import org.grupo3.proyectofinalspring.infraestructure.rest.cliente.ClienteReniec;
import org.grupo3.proyectofinalspring.seguridad.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AuthenticationServiceAdapterTest {
    @Mock
    private  UsuarioRepository usuarioRepository;
    @Mock
    private  AuthenticationManager authenticationManager;
    @Mock
    private  JWTService jwtService;
    @Mock
    private  ClienteRepository clienteRepository;
    @Mock
    private  DireccionRepository direccionRepository;
    @Mock
    private  RolRepository rolRepository;
    @Mock
    private  UsuarioMapper usuarioMapper;
    @Mock
    private  ClienteReniec clienteReniec;
    @InjectMocks
    private AuthenticationServiceAdapter authenticationServiceAdapter;
    private SignUpRequest signUpRequest;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        signUpRequest = SignUpRequest.builder()
                .dni("73005607")
                .direccion("av morales duarez 173")
                .ciudad("Callao")
                .provincia("Callao")
                .telefonoCont("930889076")
                .email("pantajefferson173@gmail.com")
                .nomUsuario("RPantaX")
                .password("SiSoy1928#.")
                .build();
    }

    @Test
    void signUpClienteSucess() {
        //mock para Request Cliente
        //esta en el before
        //direccion
        ReflectionTestUtils.setField(authenticationServiceAdapter,"tokenApi","XXXXXXXX",String.class);
        ResponseReniec responseReniec = getResponseReniec();
        Optional<DireccionEntity> direccionGuardada = registerDireccion(signUpRequest);
        //cliente
        Optional<ClienteEntity> clienteGuardado = registerCLienteWithReniec(signUpRequest, direccionGuardada, responseReniec);
        //roles
        RolEntity rol = RolEntity.builder()
                .nombreRol("USER")
                .estado(1)
                .usuaCrea("JEFFERSON")
                .build();
        Set<RolDTO> rolDtoSet = new HashSet<>();
        RolDTO rolDTO = new RolDTO(1L, "USER");
        rolDtoSet.add(rolDTO);
        //UsuarioDTO Esperado
        UsuarioDTO usuarioDtoEsperado = registerUsuario(signUpRequest,clienteGuardado, rolDtoSet );

        //Comportamiento
        when(clienteReniec.getInfoReniec(anyString(), anyString())).thenReturn(responseReniec);
        when(direccionRepository.save(any(DireccionEntity.class))).thenReturn(direccionGuardada.get());
        when(direccionRepository.findById(anyLong())).thenReturn(direccionGuardada);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteGuardado.get());
        when(clienteRepository.findById(anyLong())).thenReturn(clienteGuardado);
        when(rolRepository.findByNombreRol("USER")).thenReturn(Optional.of(rol));
        when(usuarioMapper.mapUsuarioToDto(any())).thenReturn(usuarioDtoEsperado);

        ResponseEntity <UsuarioDTO> usuarioDTORecibido = authenticationServiceAdapter.signUpCliente(signUpRequest);
        //verificar resultados
        assertEquals(usuarioDtoEsperado.getIdUsuario(), usuarioDTORecibido.getBody().getIdUsuario());
        assertEquals(usuarioDtoEsperado.getClienteDTO(), usuarioDTORecibido.getBody().getClienteDTO());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getIdCliente(), usuarioDTORecibido.getBody().getClienteDTO().getIdCliente());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDni(), usuarioDTORecibido.getBody().getClienteDTO().getDni());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getNombres(), usuarioDTORecibido.getBody().getClienteDTO().getNombres());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getApePaterno(), usuarioDTORecibido.getBody().getClienteDTO().getApePaterno());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getApeMaterno(), usuarioDTORecibido.getBody().getClienteDTO().getApeMaterno());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDireccionDTO(), usuarioDTORecibido.getBody().getClienteDTO().getDireccionDTO());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDireccionDTO().getDireccion(), usuarioDTORecibido.getBody().getClienteDTO().getDireccionDTO().getDireccion());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDireccionDTO().getCiudad(), usuarioDTORecibido.getBody().getClienteDTO().getDireccionDTO().getCiudad());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDireccionDTO().getProvincia(), usuarioDTORecibido.getBody().getClienteDTO().getDireccionDTO().getProvincia());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDireccionDTO().getEmail(), usuarioDTORecibido.getBody().getClienteDTO().getDireccionDTO().getEmail());
        assertEquals(usuarioDtoEsperado.getClienteDTO().getDireccionDTO().getTelefonoCont(), usuarioDTORecibido.getBody().getClienteDTO().getDireccionDTO().getTelefonoCont());
        assertEquals(usuarioDtoEsperado.getRolDTOS(), usuarioDTORecibido.getBody().getRolDTOS());
        assertEquals(usuarioDtoEsperado.getNomUsuario(), usuarioDTORecibido.getBody().getNomUsuario());
        assertEquals(usuarioDtoEsperado.getIdUsuario(), usuarioDTORecibido.getBody().getIdUsuario());
    }
    @Test
    void signUpClienteError(){
        //mock para Request Cliente
        //esta en el before
        ReflectionTestUtils.setField(authenticationServiceAdapter,"tokenApi","XXXXXXXX",String.class);
        ResponseReniec responseReniec = getResponseReniec();
        Optional<DireccionEntity> direccionGuardada = registerDireccion(signUpRequest);
        //cliente
        Optional<ClienteEntity> clienteGuardado = registerCLienteWithReniec(signUpRequest, direccionGuardada, responseReniec);

        //Comportamiento
        when(clienteReniec.getInfoReniec(anyString(), anyString())).thenReturn(responseReniec);
        when(direccionRepository.save(any(DireccionEntity.class))).thenReturn(direccionGuardada.get());
        when(direccionRepository.findById(anyLong())).thenReturn(direccionGuardada);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteGuardado.get());
        when(clienteRepository.findById(anyLong())).thenReturn(clienteGuardado);

        assertThrows(Exception.class,() -> authenticationServiceAdapter.signUpCliente(signUpRequest));

    }

    @Test
    void signInSuccess() throws Exception {
        ReflectionTestUtils.setField(authenticationServiceAdapter,"tokenApi","XXXXXXXX",String.class);
        ResponseReniec responseReniec = getResponseReniec();
        SignInRequest signInRequest = new SignInRequest("RPantaX", "SiSoy1928#.");
        Optional<DireccionEntity> direccionGuardada = registerDireccion(signUpRequest);
        //cliente
        Optional<ClienteEntity> clienteGuardado = registerCLienteWithReniec(signUpRequest, direccionGuardada, responseReniec);
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .idUsuario(1L)
                .nomUsuario("RPantaX")
                .password(new BCryptPasswordEncoder().encode("SiSoy1928#."))
                .estado(1)
                .usuaCrea("RPantaX")
                .accountnonlocked(true)
                .accountnonexpire(true)
                .credentialsnonexpired(true)
                .clienteEntity(clienteGuardado.get())
                .enabled(true)
                .build();

        // Behavior setup
        when(usuarioRepository.findByNomUsuario(signInRequest.getUsername())).thenReturn(Optional.of(usuarioEntity));
        when(jwtService.generateToken(usuarioEntity)).thenReturn("Tokeeeeeeeeeeeen");
        // Execution
        AuthenticationResponse response = authenticationServiceAdapter.signIn(signInRequest);

        // Assertion
        assertNotNull(response);
        assertEquals("Tokeeeeeeeeeeeen", response.getToken()); // Assuming this is what's expected
    }
    @Test
    void signInError() throws Exception {
        // Mocking
        SignInRequest signInRequest = new SignInRequest("username", "SiSoy1928#.");
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setPassword("SiSoy1928#.");

        // Behavior setup
        when(usuarioRepository.findByNomUsuario(anyString())).thenReturn(Optional.of(usuarioEntity));

        // Execution
        AuthenticationResponse response = authenticationServiceAdapter.signIn(signInRequest);

        // Assertion
        assertNotNull(response);
        assertEquals("la authenticación falló", response.getToken()); // Assuming this is what's expected
    }


    private ResponseReniec getResponseReniec(){
        return new ResponseReniec("Jefferon", "Panta", "Ruiz", "DNI","73005607","2");
    }
    private Optional<ClienteEntity> registerCLienteWithReniec(SignUpRequest signUpRequest, Optional<DireccionEntity> direccionGuardada, ResponseReniec responseReniec) {

        return Optional.of(ClienteEntity.builder()
                .idCliente(1L)
                .dni(signUpRequest.getDni())
                .nombres(responseReniec.getNombres())
                .apePaterno(responseReniec.getApellidoPaterno())
                .apeMaterno(responseReniec.getApellidoMaterno())
                .direccionEntity(direccionGuardada.get())
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario())
                .build());
    }

    private Optional<DireccionEntity> registerDireccion(SignUpRequest signUpRequest) {
        return Optional.of(DireccionEntity.builder()
                .idDireccion(1L)
                .direccion(signUpRequest.getDireccion())
                .calle(signUpRequest.getCalle())
                .ciudad(signUpRequest.getCiudad())
                .provincia(signUpRequest.getProvincia())
                .numero(signUpRequest.getNumero())
                .telefonoCont(signUpRequest.getTelefonoCont())
                .email(signUpRequest.getEmail())
                .estado(1)
                .usuaCrea(signUpRequest.getNomUsuario())
                .build());
    }
    private UsuarioDTO registerUsuario(SignUpRequest signUpRequest, Optional<ClienteEntity> clienteGuardado, Set<RolDTO> rolDTOS){
        return UsuarioDTO.builder()
                .idUsuario(1L)
                .clienteDTO(ClienteDTO.builder()
                        .idCliente(1L)
                        .dni(clienteGuardado.get().getDni())
                        .nombres(clienteGuardado.get().getNombres())
                        .apeMaterno(clienteGuardado.get().getApeMaterno())
                        .apePaterno(clienteGuardado.get().getApePaterno())
                        .direccionDTO(DireccionDTO.builder()
                                .idDireccion(1L)
                                .direccion(signUpRequest.getDireccion())
                                .email(signUpRequest.getEmail())
                                .numero(signUpRequest.getNumero())
                                .ciudad(signUpRequest.getCalle())
                                .provincia(signUpRequest.getProvincia())
                                .piso(signUpRequest.getPiso())
                                .telefonoCont(signUpRequest.getTelefonoCont()).build())
                        .build())
                .nomUsuario(signUpRequest.getNomUsuario())
                .rolDTOS(rolDTOS)
                .enabled(true)
                .accountnonexpire(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .build();
    }


}