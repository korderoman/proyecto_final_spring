package org.grupo3.proyectofinalspring.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Long idUsuario;
    private String nomUsuario;
    private ClienteDTO clienteDTO;
    private Set<RolDTO> rolDTOS;
    private boolean enabled;
    private boolean accountnonexpire;
    private boolean accountnonlocked;
    private boolean credentialsnonexpired;
}
