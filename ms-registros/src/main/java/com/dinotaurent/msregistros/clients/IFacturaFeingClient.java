package com.dinotaurent.msregistros.clients;

import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-facturas")
public interface IFacturaFeingClient {

    @GetMapping("/facturas-pagadas")
    List<Factura> facturasPagadas();
}
