package com.dinotaurent.msfacturas.models.services;

import com.dinotaurent.mscommons.models.services.CommonServiceImpl;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import com.dinotaurent.msfacturas.models.clients.IProductoFeingClient;
import com.dinotaurent.msfacturas.models.dao.IFacturaDao;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl extends CommonServiceImpl<Factura, IFacturaDao> implements IFacturaService{

    @Autowired
    private IProductoFeingClient productoClient;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findByPagadaTrue() {
        return dao.findByPagadaTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> findByProductosId(Long productosId) {
        return dao.findByProductosId(productosId);
    }

    @Override
    public Optional<Producto> listarXId(Long id) {
        return productoClient.listarXId(id);
    }

    @Override
    public void actualizarDisponibles(Long id, Producto producto) {
        productoClient.actualizarDisponibles(id,producto);
    }
}
