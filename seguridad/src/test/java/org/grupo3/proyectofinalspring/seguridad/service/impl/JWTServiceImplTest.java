package org.grupo3.proyectofinalspring.seguridad.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JWTServiceImplTest {
    @Mock
    private Resource privateKeyResource;

    @InjectMocks
    private JWTServiceImpl jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void generateToken() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Mocking
        mockPrivateKeyResource("your_private_key_here");
        String username = "testuser";
        String token = null;
        try {
            token = jwtService.generateToken(mockUserDetails(username));
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // Assertion
        assertNotNull(token);
    }

    @Test
    void validateToken() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Mocking
        String username = "testuser";
        String token = null;
        try {
            token = jwtService.generateToken(mockUserDetails(username));
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // Assertion
        assertTrue(jwtService.validateToken(token, mockUserDetails(username)));
    }

    @Test
    void extractUserName() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Mocking
        String username = "testuser";
        String token = null;
        try {
            token = jwtService.generateToken(mockUserDetails(username));
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // Execution
        String extractedUsername = jwtService.extractUserName(token);

        // Assertion
        assertEquals(username, extractedUsername);
    }

    // Utility method to mock UserDetails
    private UserDetails mockUserDetails(String username) {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(username);
        return userDetails;
    }

    // Utility method to mock a private key resource
    private void mockPrivateKeyResource(String key) {
        try {
            when(privateKeyResource.getInputStream()).thenReturn(new ByteArrayInputStream(key.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}