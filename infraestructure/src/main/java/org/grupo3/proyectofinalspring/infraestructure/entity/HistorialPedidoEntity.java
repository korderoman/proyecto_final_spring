package org.grupo3.proyectofinalspring.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hist_pedido")
public class HistorialPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hist_pedido")
    private Long idHistorialPedido;
    @Column(name = "fecha_inicio_pedido")
    private Timestamp fechaInicioPedido;
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
    /*coneccion con las otras tablas*/


}
