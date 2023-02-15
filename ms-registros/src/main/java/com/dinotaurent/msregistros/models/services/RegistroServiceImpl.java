package com.dinotaurent.msregistros.models.services;

import com.dinotaurent.mscommonsproductosfactura.models.entity.Factura;
import com.dinotaurent.msregistros.clients.IFacturaFeingClient;
import com.dinotaurent.msregistros.models.dao.IRegistroDao;
import com.dinotaurent.msregistros.models.entity.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroServiceImpl implements IRegistroService{

    @Autowired
    private IFacturaFeingClient client;

    @Autowired
    private IRegistroDao dao;
    @Override
    public List<Registro> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Registro> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Registro insert(Registro registro) {
        return dao.insert(registro);
    }

    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<Factura> facturasPagadas() {
        return client.facturasPagadas();
    }
}
