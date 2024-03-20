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
@Table(name = "envio")
public class EnvioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long id_envio;
    @Column(name = "forma_pago", length = 30)
    private String forma_pago;
    @Column(name = "fec_ini_ped", nullable = false)
    private Timestamp fec_ini_ped;
    @Column(name = "fec_fin_ped", nullable = false)
    private Timestamp fec_fin_ped;
    @Column(name = "fecha_registro", nullable = false)
    private Timestamp fecha_registro;

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
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedidoEntity;
     */
}
