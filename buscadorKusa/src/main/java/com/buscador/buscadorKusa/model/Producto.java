package com.buscador.buscadorKusa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String descripcion;

    private String unidad;


    private BigDecimal precioUnitario;

    private BigDecimal precioCaja;

    private BigDecimal precioDistribuidor;

    private Integer cantidadCaja;

}