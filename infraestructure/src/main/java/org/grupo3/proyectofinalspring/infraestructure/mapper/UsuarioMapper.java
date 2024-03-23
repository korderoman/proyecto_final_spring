package org.grupo3.proyectofinalspring.infraestructure.mapper;

import org.grupo3.proyectofinalspring.domain.aggregates.dto.ClienteDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.DireccionDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.RolDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.UsuarioDTO;
import org.grupo3.proyectofinalspring.infraestructure.entity.ClienteEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.DireccionEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.RolEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    static {
        // Configurar ModelMapper para mapear ClienteEntity a ClienteDTO
        modelMapper.typeMap(ClienteEntity.class, ClienteDTO.class)
                .addMappings(mapper -> mapper.map(ClienteEntity::getIdCliente, ClienteDTO::setIdCliente))
                .addMappings(mapper ->mapper.map(ClienteEntity::getDireccionEntity, ClienteDTO::setDireccionDTO));

        // Configurar ModelMapper para mapear RolEntity a RolDTO
        modelMapper.typeMap(RolEntity.class, RolDTO.class)
                .addMappings(mapper -> mapper.map(RolEntity::getIdRol, RolDTO::setIdRol));

        // Configurar ModelMapper para mapear UsuarioEntity a UsuarioDTO
        modelMapper.typeMap(UsuarioEntity.class, UsuarioDTO.class)
                .addMappings(mapper -> mapper.map(UsuarioEntity::getClienteEntity, UsuarioDTO::setClienteDTO))
                .addMappings(mapper -> mapper.map(UsuarioEntity::getRoles, UsuarioDTO::setRolDTOS));
    }
    public UsuarioDTO mapUsuarioToDto(UsuarioEntity usuario){return modelMapper.map(usuario, UsuarioDTO.class);}
}
