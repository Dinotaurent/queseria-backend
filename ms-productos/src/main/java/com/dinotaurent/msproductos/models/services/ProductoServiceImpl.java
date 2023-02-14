package com.dinotaurent.msproductos.models.services;

import com.dinotaurent.mscommons.models.services.CommonServiceImpl;
import com.dinotaurent.mscommonsproductos.models.entity.Producto;
import com.dinotaurent.msproductos.models.dao.IProductoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, IProductoDao> implements IProductoService{
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombreContaining(String termino) {
        return dao.findByNombreContaining(termino);
    }
}
