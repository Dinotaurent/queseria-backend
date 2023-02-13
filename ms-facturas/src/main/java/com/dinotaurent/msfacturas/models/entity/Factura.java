package com.dinotaurent.msfacturas.models.entity;

import com.dinotaurent.mscommonsproductos.models.entity.Producto;
import jakarta.persistence.*;

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

    private Double total;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Producto> productos;

    private boolean pagada;

    public Factura(){
        this.productos = new ArrayList<>();
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addProducto(Producto productos){
        this.productos.add(productos);
    }

    public void removeProducto(Producto producto){
        this.productos.remove(producto);
    }

    public Double calcularTotal(List<Double> preciosProductos){
        Double total = 0.0;
        for (Double preciosProducto : preciosProductos) {
            total = preciosProducto + total;
        }
        this.total = total;
        return this.total;
    }

}