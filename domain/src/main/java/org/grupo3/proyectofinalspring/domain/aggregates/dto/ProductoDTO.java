package org.grupo3.proyectofinalspring.domain.aggregates.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO {
    private Long idProducto;
    private String descLargaProd;
    private String descCortaProd;
    private String empresa;
    private String marca;
    private String modelo;
    private Double precio;
    private Integer estado;
    private List<String> caracteristicas; // se entrega la descripción
    private String categoria;
}
