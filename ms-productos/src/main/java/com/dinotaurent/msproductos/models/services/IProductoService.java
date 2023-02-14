package com.dinotaurent.msproductos.models.services;

import com.dinotaurent.mscommons.models.services.ICommonService;
import com.dinotaurent.mscommonsproductos.models.entity.Producto;

import java.util.List;

public interface IProductoService extends ICommonService<Producto> {

    List<Producto> findByNombreContaining(String termino);
}
