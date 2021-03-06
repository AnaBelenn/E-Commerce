package com.Informatorio.Ecommerce.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Linea_carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idL;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carrito carrito;

    @OneToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @NotBlank
    private Integer cantidad;

    private float precioFinal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Orden orden;

    //Setters & Getters
    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Long getIdL() {
        return idL;
    }

    public void setIdL(Long idL) {
        this.idL = idL;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = producto.getPrecio() * this.cantidad;
    }


}
