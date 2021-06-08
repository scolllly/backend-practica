package com.backend.practica.mongo.repository;

import com.backend.practica.mongo.model.ClienteMongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteMongoRepository extends MongoRepository<ClienteMongo, String> {

    ClienteMongo findByMySqlId(Integer id);
    
}
