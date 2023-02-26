package com.dinotaurent.msfacturas.models.services;

import com.dinotaurent.mscommons.models.services.ICommonService;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface IFacturaService extends ICommonService<Factura> {

    List<Factura> findByPagadaTrue();

    List<Factura> findByProductosId(Long productosId);

    Optional<Producto> listarXId(Long id);

    void actualizarDisponibles(Long id, Producto producto);


}
