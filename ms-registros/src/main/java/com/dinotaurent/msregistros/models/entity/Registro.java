package com.dinotaurent.msregistros.models.entity;

import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "registros")
public class Registro {

    @Id
    private String id;

    private BigDecimal valorTotal;

//    @Transient
    private List<Factura> facturas;


    private Date createAt;


    public Registro() {
        this.facturas = new ArrayList<>();
        this.valorTotal = new BigDecimal(0);
        this.createAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigDecimal calcularValorTotal(List<BigDecimal> valorTotalFacturas){
        for (BigDecimal valorTotalFactura: valorTotalFacturas) {
            this.valorTotal = this.valorTotal.add(valorTotalFactura);
        }
        return this.valorTotal;
    }
}
