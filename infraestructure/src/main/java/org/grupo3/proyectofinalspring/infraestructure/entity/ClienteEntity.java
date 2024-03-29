package org.grupo3.proyectofinalspring.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.DireccionDTO;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "dni", nullable = false)
    private String dni;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "ape_paterno", nullable = false)
    private String apePaterno;
    @Column(name = "ape_materno", nullable = false)
    private String apeMaterno;
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
    @OneToOne
    @JoinColumn(name = "id_direccion")
    private DireccionEntity direccionEntity;

    /*
    @OneToOne(mappedBy = "clienteEntity")
    private FacturaEntity facturaEntity;*/
}
