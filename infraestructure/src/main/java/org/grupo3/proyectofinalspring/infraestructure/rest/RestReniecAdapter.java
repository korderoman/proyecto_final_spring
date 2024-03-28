package org.grupo3.proyectofinalspring.infraestructure.rest;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.response.ResponseReniec;
import org.grupo3.proyectofinalspring.domain.ports.on.RestReniecOut;
import org.grupo3.proyectofinalspring.infraestructure.rest.cliente.ClienteReniec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestReniecAdapter implements RestReniecOut {
    private final ClienteReniec reniec;
    @Value("${token.api}")
    private String tokenApi;
    @Override
    public ResponseReniec getInfoReniec(String numDoc) {
        String authorization = "Bearer " + tokenApi;
        return reniec.getInfoReniec(numDoc,authorization);
    }
}
