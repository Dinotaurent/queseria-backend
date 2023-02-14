package com.dinotaurent.msregistros.models.dao;

import com.dinotaurent.msregistros.models.entity.Registro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRegistroDao extends MongoRepository<Registro, String> {
}
