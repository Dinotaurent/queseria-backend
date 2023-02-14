package com.dinotaurent.msfacturas.controllers;

import com.dinotaurent.mscommons.controllers.CommonController;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import com.dinotaurent.msfacturas.models.services.IFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FacturaController extends CommonController<Factura, IFacturaService> {


    @GetMapping("/facturas-pagadas")
    public ResponseEntity<?> facturasPagadas(){
        return ResponseEntity.ok(service.findByPagadaTrue());
    }

    @PutMapping("/{id}/asignar-productos")
    public ResponseEntity<?> asignarProductos(@PathVariable Long id, @RequestBody List<Producto> productos) {
        Optional<Factura> o = service.findByID(id);

        if (o.isPresent()) {
            Factura facturaBd = o.get();
            List<Double> precios = new ArrayList<>();

            productos.forEach(p -> {
                Producto producto = new Producto();
                producto.setId(p.getId());
                producto.setNombre(p.getNombre());
                producto.setPrecio(p.getPrecio());
                precios.add(p.getPrecio());
                Double totalPrecios = facturaBd.calcularTotal(precios);
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
        List<Double> precios = new ArrayList<>();
        if (o.isPresent()) {
            Factura facturaBd = o.get();
            facturaBd.removeProducto(producto);
            Double totalPrecio = producto.getPrecio();
            facturaBd.setTotal(facturaBd.getTotal() - totalPrecio);


            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(facturaBd));

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/pagar-factura")
    public ResponseEntity<?> pagarFactura(@PathVariable Long id){
        Optional<Factura> o = service.findByID(id);

        if(o.isPresent()){
            o.get().setPagada(true);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(o.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
