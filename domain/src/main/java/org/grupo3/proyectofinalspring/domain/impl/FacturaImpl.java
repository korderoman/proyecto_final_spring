package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;
import org.grupo3.proyectofinalspring.domain.ports.in.FacturaServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.FacturaServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FacturaImpl implements FacturaServiceIn {

    private final FacturaServiceOut facturaServiceOut;
    @Override
    public FacturaDTO addFacturaIn(RequestFactura requestFactura) {
        return facturaServiceOut.addFacturaOut(requestFactura);
    }
    @Override
    public List<FacturaDTO> getAllFacturaIn() {
        return facturaServiceOut.getAllFacturaOut();
    }

    @Override
    public FacturaDTO getFacturaByIdIn(Long id) {
        return facturaServiceOut.getFacturaByIdOut(id);
    }

    @Override
    public FacturaDTO updateFacturaByIdIn(Long id, RequestFactura requestFactura) {
        return facturaServiceOut.updateFacturaByIdOut(id, requestFactura);
    }

    @Override
    public FacturaDTO deleteFacturaByIdIn(Long id) {
        return facturaServiceOut.deleteFacturaByIdOut(id);
    }
}
