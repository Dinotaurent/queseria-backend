package com.dinotaurent.msfacturas.models.clients;

import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "ms-productos")
public interface IProductoFeingClient {

    @GetMapping("/{id}")
    Optional<Producto> listarXId(@PathVariable Long id);

    @PutMapping("/{id}/actualizar-disponibles")
    void actualizarDisponibles(@PathVariable Long id, @RequestBody Producto producto);

}
