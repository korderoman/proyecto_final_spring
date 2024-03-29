package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;

import java.util.List;

public interface FacturaServiceIn {
    FacturaDTO addFacturaIn(RequestFactura requestFactura);
    List<FacturaDTO> getAllFacturaIn();
    FacturaDTO getFacturaByIdIn(Long id) ;
    FacturaDTO updateFacturaByIdIn(Long id, RequestFactura requestFactura);
    FacturaDTO deleteFacturaByIdIn(Long id);
}
