package org.grupo3.proyectofinalspring.seguridad.service.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.infraestructure.repository.UsuarioRepository;
import org.grupo3.proyectofinalspring.seguridad.service.UsuarioSecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioSecurityServiceImpl implements UsuarioSecurityService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return (UserDetails) usuarioRepository.findByNomUsuario(username).orElseThrow( ()->
                        new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }
}