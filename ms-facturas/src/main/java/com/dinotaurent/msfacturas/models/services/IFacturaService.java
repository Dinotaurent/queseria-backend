package com.dinotaurent.msfacturas.models.services;

import com.dinotaurent.mscommons.models.services.ICommonService;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;

import java.util.List;

public interface IFacturaService extends ICommonService<Factura> {

    List<Factura> findByPagadaTrue();
}
