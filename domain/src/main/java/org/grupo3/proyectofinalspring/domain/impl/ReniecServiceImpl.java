package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.response.ResponseReniec;
import org.grupo3.proyectofinalspring.domain.ports.in.ReniecServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.RestReniecOut;


@RequiredArgsConstructor
public class ReniecServiceImpl implements ReniecServiceIn {
    private final RestReniecOut reniec;
    @Override
    public ResponseReniec getInfoIn(String numDoc) {
        return reniec.getInfoReniec(numDoc);
    }
}
