package org.grupo3.proyectofinalspring.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
