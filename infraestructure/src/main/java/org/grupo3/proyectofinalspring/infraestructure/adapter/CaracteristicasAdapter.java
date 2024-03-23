package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCaracteristicas;
import org.grupo3.proyectofinalspring.domain.ports.on.CaracteristicasServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.CaracteristicasEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CaracteristicasRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaracteristicasAdapter implements CaracteristicasServiceOut {

    private final CaracteristicasRepository caracteristicasRepository;
    @Override
    public CaracteristicasDTO addCaracteristicsOut(RequestCaracteristicas requestCaracteristicas) {
        CaracteristicasEntity caracteristicasEntity = new CaracteristicasEntity();
        caracteristicasEntity.setDescripcion(requestCaracteristicas.getDescripcion());
        caracteristicasEntity.setEstado(requestCaracteristicas.getEstado());
        caracteristicasEntity.setUsuaCrea("Henry Medina");
        caracteristicasEntity.setDateCreate(getTimestamp());
        CaracteristicasEntity caracteristicasSaved= caracteristicasRepository.save(caracteristicasEntity);
        return  setCaracteristicaEntityToDto(caracteristicasSaved);
    }

    @Override
    public List<CaracteristicasDTO> getAllCaracteristicsOut() {
        List<CaracteristicasEntity> caracteristicasEntities = caracteristicasRepository.findAll();
        List<CaracteristicasDTO> caracteristicasDTOS = new ArrayList<>();
        for (CaracteristicasEntity e: caracteristicasEntities){
            caracteristicasDTOS.add(setCaracteristicaEntityToDto(e));
        }

        return caracteristicasDTOS;
    }

    @Override
    public Optional<CaracteristicasDTO> getCaracteristicsByIdOut(Long id) {
        return Optional.empty();
    }

    @Override
    public CaracteristicasDTO updateCaracteristicsByIdOut(Long id, RequestCaracteristicas requestCaracteristicas) {
        Optional<CaracteristicasEntity>caracteristicasEntity = caracteristicasRepository.findById(id);
        if(caracteristicasEntity.isEmpty()){
            return  addCaracteristicsOut(requestCaracteristicas);
        }
        CaracteristicasEntity caracteristicasFound=caracteristicasEntity.get();
        caracteristicasFound.setDescripcion(requestCaracteristicas.getDescripcion());
        caracteristicasFound.setEstado(requestCaracteristicas.getEstado());
        caracteristicasFound.setUsuaModif("Henry Medina");
        caracteristicasFound.setDateModif(getTimestamp());
        CaracteristicasEntity caracteristicasUpdated=caracteristicasRepository.save(caracteristicasFound);
        return  setCaracteristicaEntityToDto(caracteristicasUpdated);

    }

    @Override
    public CaracteristicasDTO deleteCaracteristicsByIdOut(Long id) {
        boolean exist=caracteristicasRepository.existsById(id);
        if(exist){
            Optional<CaracteristicasEntity>caracteristicasEntity = caracteristicasRepository.findById(id);
            if(caracteristicasEntity.isPresent()){
                caracteristicasEntity.get().setEstado(0);
                caracteristicasEntity.get().setUsuaDelet("Henry Medina");
                caracteristicasEntity.get().setDateDelet(getTimestamp());
                CaracteristicasEntity caracteristicasDeleted=caracteristicasRepository.save(caracteristicasEntity.get());
                return  setCaracteristicaEntityToDto(caracteristicasDeleted);
            }
            return  null;
        }
        return null;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    private CaracteristicasDTO setCaracteristicaEntityToDto(CaracteristicasEntity caracteristicasEntity){
        CaracteristicasDTO caracteristicasDTO = new CaracteristicasDTO();
        caracteristicasDTO.setIdCaracteristicas(caracteristicasEntity.getId_caracteristicas());
        caracteristicasDTO.setEstado(caracteristicasEntity.getEstado());
        caracteristicasDTO.setDescripcion(caracteristicasEntity.getDescripcion());
        return caracteristicasDTO;
    }
}
