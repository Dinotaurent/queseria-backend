package com.dinotaurent.mscommonsproductosfactura.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal total;

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Producto> productos;

    @JsonIgnore
    private boolean pagada;

    public Factura() {
        this.productos = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Date createAt;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addProducto(Producto productos) {
        this.productos.add(productos);
    }

    public void removeProducto(Producto producto) {
        this.productos.remove(producto);
    }

    public BigDecimal calcularTotal(List<BigDecimal> preciosProductos) {
        BigDecimal totalSuma = BigDecimal.ZERO;
        for (BigDecimal precioProducto : preciosProductos) {
            totalSuma = this.total.add(precioProducto);
        }
        this.total = totalSuma;
        return this.total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Factura)) {
            return false;
        }
        Factura f = (Factura) obj;


        return this.id != null && this.id.equals(f.getId());
    }

}