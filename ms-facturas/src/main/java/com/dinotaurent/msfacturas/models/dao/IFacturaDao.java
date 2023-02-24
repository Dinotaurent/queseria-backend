package com.dinotaurent.msfacturas.models.dao;

import com.dinotaurent.mscommons.models.services.CrudAndSortingRepository;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFacturaDao extends CrudAndSortingRepository<Factura, Long> {

    @Query("select f from Factura as f where f.pagada = true and f.createAt = curdate()")
    List<Factura> findByPagadaTrue();

    List<Factura> findByProductosId(Long productosId);

}
