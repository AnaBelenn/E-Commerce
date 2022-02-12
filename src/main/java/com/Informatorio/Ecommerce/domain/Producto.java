package com.Informatorio.Ecommerce.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @NotBlank(message = "Campo obligatorio")
    private String nombre;

    private String descripcion;

    @NotBlank
    private float precio;

    @NotBlank
    private String contenido;

    @CreationTimestamp
    private LocalDateTime FechaDeCreacion;

    private Boolean publicado;


    // Setters & Getters
    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
        this.idP = idP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaDeCreacion() {
        return FechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        FechaDeCreacion = fechaDeCreacion;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }


    // Constructores
    public Producto() {
    }

    public Producto(Long idP, String nombre, String descripcion, float precio, String contenido, LocalDateTime fechaDeCreacion, Boolean publicado) {
        this.idP = idP;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.contenido = contenido;
        FechaDeCreacion = fechaDeCreacion;
        this.publicado = publicado;
    }
}
