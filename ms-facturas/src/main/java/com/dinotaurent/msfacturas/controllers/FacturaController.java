package com.dinotaurent.msfacturas.controllers;

import com.dinotaurent.mscommons.controllers.CommonController;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import com.dinotaurent.msfacturas.models.services.IFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class FacturaController extends CommonController<Factura, IFacturaService> {


    @GetMapping("/facturas-pagadas")
    public ResponseEntity<?> facturasPagadas() {
        return ResponseEntity.ok(service.findByPagadaTrue());
    }

    @PutMapping("/{id}/asignar-producto")
    public ResponseEntity<?> asignarProductos(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Factura> of = service.findByID(id);

        if (of.isPresent()) {
            Factura facturaBd = of.get();
            BigDecimal precio = BigDecimal.ZERO;


            Producto productoAGuardar = new Producto();
            productoAGuardar.setId(producto.getId());
            productoAGuardar.setNombre(producto.getNombre());
            productoAGuardar.setPrecio(producto.getPrecio());
            precio = producto.getPrecio();
            BigDecimal totalPrecios = facturaBd.calcularTotal(precio);
            facturaBd.setTotal(totalPrecios);
            facturaBd.addProducto(productoAGuardar);

            Optional<Producto> op = service.listarXId(producto.getId());
            if (op.isPresent()) {
                Producto productoBd = op.get();
                if (productoBd.getDisponibles() != 0) {
                    int disponibles = productoBd.getDisponibles() - 1;
                    disponibles = Math.max(disponibles, 0);
                    productoBd.setDisponibles(disponibles);
                    service.actualizarDisponibles(producto.getId(), productoBd);
                } else {
                    throw new RuntimeException("No existen productos diponibles para agregar a la factura");
                }
            }

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

            Optional<Producto> op = service.listarXId(producto.getId());
            if (op.isPresent()) {
                Producto productoBd = op.get();
                int disponibles = productoBd.getDisponibles() + 1;
                disponibles = Math.max(disponibles, 0);
                productoBd.setDisponibles(disponibles);
                service.actualizarDisponibles(producto.getId(), productoBd);
            }


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
    public ResponseEntity<?> quitarProductoEliminado(@PathVariable Long productosId) {
        List<Factura> facturas = service.findByProductosId(productosId);
        AtomicReference<BigDecimal> productoPrecio = new AtomicReference<>(BigDecimal.ZERO);

        if (!facturas.isEmpty()) {
            facturas.forEach(factura -> {
                factura.getProductos().forEach(producto -> {
                    if (producto.getId().equals(productosId)) {
                        productoPrecio.set(producto.getPrecio().add(productoPrecio.get()));
                    }
                });
                factura.getProductos().removeIf(producto -> producto.getId().equals(productosId));
                factura.setTotal(factura.getTotal().subtract(productoPrecio.get()));
                service.save(factura);
            });
        }
        return ResponseEntity.noContent().build();
    }


//    @PutMapping("/{productosId}/quitar-productos-eliminados")
//    public ResponseEntity<?> quitarProductoEliminado(@PathVariable Long productosId) {
//        List<Factura> facturas = service.findByProductosId(productosId);
//        AtomicReference<BigDecimal> productoPrecio = new AtomicReference<>(BigDecimal.ZERO);
//
//        if (!facturas.isEmpty()) {
//            facturas.forEach(factura -> {
//                factura.getProductos().removeIf(producto -> {
//                    if (producto.getId().equals(productosId)) {
//                        productoPrecio.set(producto.getPrecio());
//                        return true;
//                    }
//                    return false;
//                });
//                factura.setTotal(factura.getTotal().subtract(productoPrecio.get()));
//                service.save(factura);
//            });
//        }
//        return ResponseEntity.noContent().build();
//    }

}
