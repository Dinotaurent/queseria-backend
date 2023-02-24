package com.dinotaurent.msproductos.models.services;

import com.dinotaurent.mscommons.models.services.CommonServiceImpl;
import com.dinotaurent.mscommonsproductosfactura.models.entity.*;
import com.dinotaurent.msproductos.models.clients.IFacturaFeingClient;
import com.dinotaurent.msproductos.models.dao.IProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, IProductoDao> implements IProductoService{

    @Autowired
    private IFacturaFeingClient facturaClient;
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombreContaining(String termino) {
        return dao.findByNombreContaining(termino);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        facturaClient.quitarProductoEliminado(id);
    }
}
