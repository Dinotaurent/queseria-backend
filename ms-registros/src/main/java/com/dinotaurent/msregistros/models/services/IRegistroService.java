package com.dinotaurent.msregistros.models.services;

import com.dinotaurent.msregistros.models.entity.Registro;

import java.util.List;
import java.util.Optional;

public interface IRegistroService {

    List<Registro> findAll();

    Optional<Registro> findById(String id);

    Registro insert(Registro registro);

    void deleteById(String id);

}
