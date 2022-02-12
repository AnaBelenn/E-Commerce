package com.Informatorio.Ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private Device generadoPor;

    @CreationTimestamp
    private LocalDateTime FechaCreacion;

    @UpdateTimestamp
    private LocalDateTime FechaModificacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Transient
    private String nombreUsuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea_carrito> lineasCarrito = new ArrayList<>();

    @NotBlank
    private boolean estado;

    // Setters & Getters
    public Long getIdC() {
        return idC;
    }

    public void setIdC(Long idC) {
        this.idC = idC;
    }

    public Device getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(Device generadoPor) {
        this.generadoPor = generadoPor;
    }

    public LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return FechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return usuario.getNombre();
    }

    public List<Linea_carrito> getLineasCarrito() {
        return lineasCarrito;
    }

    public void setLineasCarrito(List<Linea_carrito> lineasCarrito) {
        this.lineasCarrito = lineasCarrito;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        if (this.getLineasCarrito() != null) {
            this.estado = true;
        }
    }

    public float totalFinal(){
        float total = 0;
        for (Linea_carrito linea : this.lineasCarrito){
            total += linea.getPrecioFinal();
        }
        return total;
    }

    public void agregarLineaCarrito(Linea_carrito linea_carrito){
        lineasCarrito.add(linea_carrito);
        linea_carrito.setCarrito(this);
    }

    public void borrarLineaCarrito(Linea_carrito linea_carrito){
        lineasCarrito.remove(linea_carrito);
        linea_carrito.setCarrito(null);
    }
}

