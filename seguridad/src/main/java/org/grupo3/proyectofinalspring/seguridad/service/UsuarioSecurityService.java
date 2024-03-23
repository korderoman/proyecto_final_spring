package org.grupo3.proyectofinalspring.seguridad.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioSecurityService {
    UserDetailsService userDetailsService();
}
