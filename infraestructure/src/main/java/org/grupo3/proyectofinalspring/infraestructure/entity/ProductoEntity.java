package org.grupo3.proyectofinalspring.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id_producto;
    @Column(name = "desc_larga_prod", length = 250)
    private String desc_larga_prod;
    @Column(name = "desc_corta_prod", length = 250)
    private String desc_corta_prod;
    @Column(name = "empresa", length = 50)
    private String empresa;
    @Column(name = "marca", length = 30)
    private String marca;
    @Column(name = "modelo", length = 30)
    private String modelo;
    @Column(name = "fec_ini_vlg ")
    private Timestamp fec_ini_vlg ;
    @Column(name = "fec_fin_vlg ")
    private Timestamp fec_fin_vlg ;

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
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;
    @OneToMany(mappedBy = "productos")
    @JsonIgnoreProperties("productos")
    private List<CaracteristicasEntity> caracteristicas;
    @OneToOne
    @JoinColumn(name="id_precio")
    private PrecioEntity precio;

}
