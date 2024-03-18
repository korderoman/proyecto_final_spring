package org.grupo3.proyectofinalspring.seguridad.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface JWTService {
    String generateToken (UserDetails userDetails)throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;
    boolean validateToken (String token, UserDetails userDetails) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;
    String extractUserName(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException;
}
