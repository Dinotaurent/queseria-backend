package com.dinotaurent.mscommonsproductosfactura.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50)
    private String nombre;

    @NotNull
    @Min(1)
    @Max(999999)
    private Double precio;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHashCode() {
        return this.foto != null ? this.foto.hashCode() : null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Producto)) {
            return false;
        }
        Producto p = (Producto) obj;


        return this.id != null && this.id.equals(p.getId());
    }
}