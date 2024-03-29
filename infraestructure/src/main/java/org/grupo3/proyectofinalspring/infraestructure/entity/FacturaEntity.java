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
@Table(name = "factura")
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long id_factura;
    @Column(name = "num_factura", length = 50)
    private String num_factura;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "igv")
    private double igv;
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
    @JoinColumn(name = "id_cliente")
    private ClienteEntity clienteEntity;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedidoEntity;

     */

}
