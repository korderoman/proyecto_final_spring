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
@Table(name = "estado")
public class EstadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id_estado;
    @Column(name = "est_inicial", length = 100)
    private Integer est_inicial ;
    @Column(name = "fec_tran")
    private Timestamp fec_tran;
    @Column(name = "fecha_ingreso")
    private Timestamp fecha_ingreso;


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
