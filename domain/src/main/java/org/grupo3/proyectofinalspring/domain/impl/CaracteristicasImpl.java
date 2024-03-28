package org.grupo3.proyectofinalspring.domain.impl;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;
import org.grupo3.proyectofinalspring.domain.ports.in.CaracteristicasServiceIn;
import org.grupo3.proyectofinalspring.domain.ports.on.CaracteristicasServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaracteristicasImpl implements CaracteristicasServiceIn {

    private final CaracteristicasServiceOut caracteristicasServiceOut;
    @Override
    public CaracteristicasDTO addCaracteristicsIn(RequestCaracteristicas requestCaracteristicas) {
        return caracteristicasServiceOut.addCaracteristicsOut(requestCaracteristicas);
    }

    @Override
    public List<CaracteristicasDTO> getAllCaracteristicsIn() {
        return caracteristicasServiceOut.getAllCaracteristicsOut();
    }

    @Override
    public CaracteristicasDTO getCaracteristicsByIdIn(Long id) throws Exception {
        return caracteristicasServiceOut.getCaracteristicsByIdOut(id);
    }

    @Override
    public CaracteristicasDTO updateCaracteristicsByIdIn(Long id, RequestCaracteristicas requestCaracteristicas) {
        return  caracteristicasServiceOut.updateCaracteristicsByIdOut(id,requestCaracteristicas);
    }

    @Override
    public CaracteristicasDTO deleteCaracteristicsByIdIn(Long id) throws Exception {
        return caracteristicasServiceOut.deleteCaracteristicsByIdOut(id);
    }
}
