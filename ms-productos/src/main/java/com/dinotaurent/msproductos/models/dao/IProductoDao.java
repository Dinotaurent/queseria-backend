package com.dinotaurent.msproductos.models.dao;

import com.dinotaurent.mscommons.models.services.CrudAndSortingRepository;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;

import java.util.List;

public interface IProductoDao extends CrudAndSortingRepository<Producto,Long> {

    List<Producto> findByNombreContaining(String termino);
}
