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
@Table(name = "producto_pedido")
public class Producto_PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precio")
    private Long id_precio;
    @Column(name = "precio")
    private double precio;
    @Column(name = "oferta")
    private boolean oferta;
    @Column(name = "fec_ini_vlg")
    private Timestamp fec_ini_vlg;
    @Column(name = "fec_ini_vlg")
    private Timestamp fec_fin_vlg;

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
