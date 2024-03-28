package org.grupo3.proyectofinalspring.domain.ports.in;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;

import java.util.List;
import java.util.Optional;

public interface CaracteristicasServiceIn {
    CaracteristicasDTO addCaracteristicsIn(RequestCaracteristicas requestCaracteristicas);
    List<CaracteristicasDTO> getAllCaracteristicsIn();
    CaracteristicasDTO getCaracteristicsByIdIn(Long id) throws Exception;
    CaracteristicasDTO updateCaracteristicsByIdIn(Long id, RequestCaracteristicas requestCaracteristicas) ;
    CaracteristicasDTO deleteCaracteristicsByIdIn(Long id) throws Exception;
}
