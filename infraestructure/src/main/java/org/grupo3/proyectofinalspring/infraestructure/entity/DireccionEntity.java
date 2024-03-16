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
@Table(name = "direccion", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class DireccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long idDireccion;
    @Column(name = "direccion", nullable = false)
    private String direccion;
    @Column(name = "ciudad", nullable = false)
    private String ciudad;
    @Column(name = "provincia", nullable = false)
    private String provincia;

    private String calle;
    private String numero;
    private String piso;
    @Column(name = "codigo_postal", nullable = false)
    private String codigPostal;
    @Column(name = "telefono_cont", nullable = false)
    private String telefonoCont;
    private String email;
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
}
