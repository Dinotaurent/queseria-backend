package com.dinotaurent.msproductos.models.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ms-facturas")
public interface IFacturaFeingClient {

    @PutMapping("/{productosId}/quitar-productos-eliminados")
    void quitarProductoEliminado(@PathVariable Long productosId);
}
