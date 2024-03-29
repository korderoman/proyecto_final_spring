package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.FacturaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestFactura;
import org.grupo3.proyectofinalspring.domain.ports.on.FacturaServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.FacturaEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaAdapter implements FacturaServiceOut {
    private final FacturaRepository facturaRepository;
    @Override
    public FacturaDTO addFacturaOut(RequestFactura requestFactura) {
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setNum_factura(requestFactura.getNumFactura());
        facturaEntity.setCantidad(requestFactura.getCantidad());
        facturaEntity.setIgv(requestFactura.getIgv());
        facturaEntity.setFecha_ingreso(requestFactura.getFechaIngreso());
        facturaEntity.setUsuaCrea("Henry Medina");
        facturaEntity.setDateCreate(getTimestamp());
        FacturaEntity facturaEntity1 = facturaRepository.save(facturaEntity);
        return FacturaEntityToDto(facturaEntity1);
    }

    @Override
    public List<FacturaDTO> getAllFacturaOut() {
        List<FacturaEntity> facturaEntities = facturaRepository.findAll();
        List<FacturaDTO> facturaDTOS = new ArrayList<>();
        for (FacturaEntity f: facturaEntities) {
            facturaDTOS.add(FacturaEntityToDto(f));
        }
        return facturaDTOS;
    }

    @Override
    public FacturaDTO getFacturaByIdOut(Long id){
        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(id);
        return FacturaEntityToDto(facturaEntity.get());
    }

    @Override
    public FacturaDTO updateFacturaByIdOut(Long id, RequestFactura requestFactura) {
        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(id);
        if (facturaEntity.isEmpty()){
            return addFacturaOut(requestFactura);
        }
        FacturaEntity factura = facturaEntity.get();
        factura.setNum_factura(requestFactura.getNumFactura());
        factura.setCantidad(requestFactura.getCantidad());
        factura.setIgv(requestFactura.getIgv());
        factura.setFecha_ingreso(requestFactura.getFechaIngreso());
        factura.setUsuaModif("Henry Medina");
        factura.setDateModif(getTimestamp());
        FacturaEntity facturaUpdate = facturaRepository.save(factura);
        return  FacturaEntityToDto(facturaUpdate);
    }

    @Override
    public FacturaDTO deleteFacturaByIdOut(Long id) {
        boolean exists = facturaRepository.existsById(id);
        if (exists){
            Optional<FacturaEntity> facturaEntity = facturaRepository.findById(id);
            if (facturaEntity.isPresent()){
                facturaEntity.get().setEstado(0);
                facturaEntity.get().setUsuaDelet("Henry Medina");
                facturaEntity.get().setDateDelet(getTimestamp());
                FacturaEntity facturaDelete = facturaRepository.save(facturaEntity.get());
                return FacturaEntityToDto(facturaDelete);
            }
            return null;
        }
        return null;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    private FacturaDTO FacturaEntityToDto(FacturaEntity facturaEntity){
        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setIdFactura(facturaEntity.getId_factura());
        facturaDTO.setNumFactura(facturaEntity.getNum_factura());
        facturaDTO.setCantidad(facturaEntity.getCantidad());
        facturaDTO.setIgv(facturaEntity.getIgv());
        return facturaDTO;
    }
}

