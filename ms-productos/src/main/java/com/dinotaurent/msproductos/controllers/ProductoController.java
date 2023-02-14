package com.dinotaurent.msproductos.controllers;

import com.dinotaurent.mscommons.controllers.CommonController;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import com.dinotaurent.msproductos.models.services.IProductoService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class ProductoController extends CommonController<Producto, IProductoService> {

    @GetMapping("/buscar-producto-x-nombre/{termino}")
    public ResponseEntity<?> buscarXNombre(@PathVariable String termino) {
        return ResponseEntity.ok(service.findByNombreContaining(termino));
    }

    @GetMapping("/ver-foto/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id) {
        Optional<Producto> o = service.findByID(id);
        if (o.isPresent() && o.get().getFoto() != null) {
            Resource imagen = new ByteArrayResource(o.get().getFoto());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Producto producto, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) {
            producto.setFoto(archivo.getBytes());
        }
        return super.crear(producto, result);
    }

    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Producto producto, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Producto> o = service.findByID(id);
        if (o.isPresent()) {
            Producto productoBD = o.get();
            productoBD.setNombre(producto.getNombre());
            productoBD.setPrecio(producto.getPrecio());
            if (!archivo.isEmpty()) {
                productoBD.setFoto(archivo.getBytes());
            }

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(productoBD));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto, BindingResult result) {
        Optional<Producto> o = service.findByID(id);
        if (o.isPresent()) {
            Producto productoBd = o.get();
            productoBd.setNombre(producto.getNombre());
            productoBd.setPrecio(producto.getPrecio());

            if (result.hasErrors()) {
                return mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(productoBd));
            }
        }
        return ResponseEntity.notFound().build();

    }
}
