package org.grupo3.proyectofinalspring.seguridad.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.seguridad.service.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    @Value("classpath:jwtKey/private_key.pem")
    private Resource privateKeyResource;

    @Override
    public String generateToken(UserDetails userDetails) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 120000))
                .signWith(getSignKey(privateKeyResource), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String extractUserName(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return extractClaims(token, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parserBuilder().setSigningKey(getSignKey(privateKeyResource)).build().parseClaimsJws(token).getBody();
    }


    public PrivateKey getSignKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes= Files.readAllBytes(Paths.get(resource.getURI())); //obtenemos la ruta de la llave publica.
        String privateKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "") //para que lea solo la parte codificada
                .replaceAll("\\s", ""); // "\\s" elimina los espacios en blanco

        byte[] decodedKey = Base64.getDecoder().decode(privateKeyPEM);//decodificamos lo que queda dentro del string, en base 64 porque nuestra privateKey esta en base 64
        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
    }
    private boolean isTokenExpired(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
