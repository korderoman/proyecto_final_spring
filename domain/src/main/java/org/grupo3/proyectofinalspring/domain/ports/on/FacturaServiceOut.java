package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;

import java.util.List;

public interface FacturaServiceOut {
    FacturaDTO addFacturaOut(RequestFactura requestFactura);
    List<FacturaDTO> getAllFacturaOut();
    FacturaDTO getFacturaByIdOut(Long id) ;
    FacturaDTO updateFacturaByIdOut(Long id, RequestFactura requestFactura);
    FacturaDTO deleteFacturaByIdOut(Long id);
}
