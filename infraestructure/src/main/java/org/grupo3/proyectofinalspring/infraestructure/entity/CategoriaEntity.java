package org.grupo3.proyectofinalspring.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id_categoria;
    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "usua_modif", length = 45)
    private String usuaModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @Column(name = "usua_delet", length = 45)
    private String usuaDelet;

    @Column(name = "date_delet")
    private Timestamp dateDelet;

    /*
    @OneToOne
    @JoinColumn(name = "id_direccion")
    private DireccionEntity direccionEntity;
     */
}
