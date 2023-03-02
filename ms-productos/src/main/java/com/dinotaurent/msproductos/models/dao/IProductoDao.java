package com.dinotaurent.msproductos.models.dao;

import com.dinotaurent.mscommons.models.services.CrudAndSortingRepository;
import com.dinotaurent.mscommonsproductosfactura.models.entity.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductoDao extends CrudAndSortingRepository<Producto,Long> {

    List<Producto> findByNombreContaining(String termino);

    @Modifying
    @Query("update Producto s set s.disponibles = 10")
    void agregarDiez();
}
