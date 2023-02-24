package com.dinotaurent.msfacturas.controllers;

import com.dinotaurent.mscommons.controllers.CommonController;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import com.dinotaurent.msfacturas.models.services.IFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FacturaController extends CommonController<Factura, IFacturaService> {


    @GetMapping("/facturas-pagadas")
    public ResponseEntity<?> facturasPagadas() {
        return ResponseEntity.ok(service.findByPagadaTrue());
    }

    @PutMapping("/{id}/asignar-productos")
    public ResponseEntity<?> asignarProductos(@PathVariable Long id, @RequestBody List<Producto> productos) {
        Optional<Factura> o = service.findByID(id);

        if (o.isPresent()) {
            Factura facturaBd = o.get();
            List<BigDecimal> precios = new ArrayList<>();

            productos.forEach(p -> {
                Producto producto = new Producto();
                producto.setId(p.getId());
                producto.setNombre(p.getNombre());
                producto.setPrecio(p.getPrecio());
                precios.add(p.getPrecio());
                BigDecimal totalPrecios = facturaBd.calcularTotal(precios);
                facturaBd.setTotal(totalPrecios);
                facturaBd.addProducto(producto);
            });
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(facturaBd));

        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}/remover-producto")
    public ResponseEntity<?> removerProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Factura> o = service.findByID(id);
        List<BigDecimal> precios = new ArrayList<>();
        if (o.isPresent()) {
            Factura facturaBd = o.get();
            facturaBd.removeProducto(producto);
            BigDecimal totalPrecio = producto.getPrecio();
            facturaBd.setTotal(facturaBd.getTotal().subtract(totalPrecio));


            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(facturaBd));

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/pagar-factura")
    public ResponseEntity<?> pagarFactura(@PathVariable Long id) {
        Optional<Factura> o = service.findByID(id);
        BigDecimal cero = BigDecimal.ZERO;

        if (o.isPresent() && o.get().getTotal().compareTo(cero) > 0) {
            o.get().setPagada(true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(o.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{productosId}/quitar-productos-eliminados")
    public ResponseEntity<?> quitarProductoEliminado(@PathVariable Long productosId){
        List<Factura> facturas = service.findByProductosId(productosId);
//        BigDecimal productoPrecio = new BigDecimal(0);

        if(!facturas.isEmpty()){
            facturas.forEach(factura -> {
                factura.getProductos().removeIf(producto -> {
                    producto.getId().equals(productosId);
                    BigDecimal productoPrecio = producto.getPrecio();
                    factura.setTotal(factura.getTotal().subtract(productoPrecio));
                    return true;
                });

                service.save(factura);
            });
        }
        return ResponseEntity.noContent().build();
    }

}
