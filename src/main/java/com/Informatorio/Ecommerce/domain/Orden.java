package com.Informatorio.Ecommerce.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @NotBlank
    private Long carrito_id;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea_carrito> lineaCarritos = new ArrayList<>();

    @NotBlank
    @CreationTimestamp
    private LocalDate fechaCreacion;

    @Column(length = 200)
    private String observacion;

    public Long getIdO() {
        return idO;
    }

    public void setIdO(Long idO) {
        this.idO = idO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(Long carrito_id) {
        this.carrito_id = carrito_id;
    }

    public List<Linea_carrito> getLineaCarritos() {
        return lineaCarritos;
    }

    public void setLineaCarritos(List<Linea_carrito> lineaCarritos) {
        this.lineaCarritos = lineaCarritos;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Orden() {
    }
}
