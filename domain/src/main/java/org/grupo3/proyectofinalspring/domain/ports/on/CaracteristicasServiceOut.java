package org.grupo3.proyectofinalspring.domain.ports.on;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;

import java.util.List;
import java.util.Optional;

public interface CaracteristicasServiceOut {
    CaracteristicasDTO addCaracteristicsOut(RequestCaracteristicas requestCaracteristicas);
    List<CaracteristicasDTO> getAllCaracteristicsOut();
    Optional<CaracteristicasDTO> getCaracteristicsByIdOut(Long id);
    CaracteristicasDTO updateCaracteristicsByIdOut(Long id, RequestCaracteristicas requestCaracteristicas);
    CaracteristicasDTO deleteCaracteristicsByIdOut(Long id);
}
