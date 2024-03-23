package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CategoriaDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestCategoria;
import org.grupo3.proyectofinalspring.domain.ports.on.CategoriaServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.CategoriaEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaAdapter implements CategoriaServiceOut {
    private final CategoriaRepository categoriaRepository;
    @Override
    public CategoriaDTO addCategoryOut(RequestCategoria requestCategoria) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setDescripcion(requestCategoria.getDescripcion());
        categoria.setEstado(requestCategoria.getEstado());
        categoria.setUsuaCrea("Henry Medina");
        categoria.setDateCreate(getTimestamp());
        CategoriaEntity categoriaEntity= categoriaRepository.save(categoria);

        return categoriaEntityToDto(categoriaEntity);
    }

    @Override
    public List<CategoriaDTO> getAllCategoriesOut() {
        List<CategoriaEntity> categoriaEntities = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        for(CategoriaEntity e :categoriaEntities){
            categoriaDTOS.add(categoriaEntityToDto(e));
        }

        return categoriaDTOS;

    }

    @Override
    public Optional<CategoriaDTO> getCategoryByIdOut(Long id) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
        return categoriaEntity.map(this::categoriaEntityToDto);
    }

    @Override
    public CategoriaDTO updateCategoryByIdOut(Long id, RequestCategoria requestCategoria) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
        if(categoriaEntity.isEmpty()){
            return  addCategoryOut(requestCategoria);
        }
        CategoriaEntity categoriaFinded= categoriaEntity.get();
        categoriaFinded.setUsuaModif("Henry Medina");
        categoriaFinded.setDateModif(getTimestamp());
        CategoriaEntity categoriaUpdated= categoriaRepository.save(categoriaFinded);
        return categoriaEntityToDto(categoriaUpdated);

    }

    @Override
    public CategoriaDTO deleteCategoryByIdOut(Long id) {
        boolean exist = categoriaRepository.existsById(id);
        if(exist){
            Optional<CategoriaEntity> categoriaEntity= categoriaRepository.findById(id);
            if(categoriaEntity.isPresent()){
                CategoriaEntity categoriaFinded= categoriaEntity.get();
                categoriaFinded.setEstado(0);
                categoriaFinded.setUsuaDelet("Henry Medina");
                categoriaFinded.setDateDelet(getTimestamp());
                CategoriaEntity categoriaSaved=categoriaRepository.save(categoriaFinded);
                return  categoriaEntityToDto(categoriaSaved);
            }
            return  null;
        }
        return  null;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }

    private CategoriaDTO categoriaEntityToDto(CategoriaEntity categoriaEntity){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(categoriaEntity.getId_categoria());
        categoriaDTO.setEstado(categoriaEntity.getEstado());
        categoriaDTO.setDescripcion(categoriaEntity.getDescripcion());
        return categoriaDTO;
    }
}
