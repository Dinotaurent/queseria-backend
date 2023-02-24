package com.dinotaurent.msfacturas.models.services;

import com.dinotaurent.mscommons.models.services.CommonServiceImpl;
import com.dinotaurent.msfacturas.models.dao.IFacturaDao;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacturaServiceImpl extends CommonServiceImpl<Factura, IFacturaDao> implements IFacturaService{

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
}
