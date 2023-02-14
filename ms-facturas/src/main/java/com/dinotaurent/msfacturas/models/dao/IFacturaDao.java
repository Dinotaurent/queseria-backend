package com.dinotaurent.msfacturas.models.dao;

import com.dinotaurent.mscommons.models.services.CrudAndSortingRepository;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;

import java.util.List;

public interface IFacturaDao extends CrudAndSortingRepository<Factura, Long> {

//    List<Factura> findByPagadaIs();

    List<Factura> findByPagadaTrue();
}
