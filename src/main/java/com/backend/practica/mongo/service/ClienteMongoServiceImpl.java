package com.backend.practica.mongo.service;

import java.util.List;

import com.backend.practica.mongo.model.ClienteMongo;
import com.backend.practica.mongo.repository.ClienteMongoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMongoServiceImpl implements IClienteMongoService {

    @Autowired
    private ClienteMongoRepository repo;

    @Override
    public ClienteMongo crearCliente(ClienteMongo c) {
        return repo.save(c);
    }

    @Override
    public ClienteMongo buscarCliente(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<ClienteMongo> buscarClientes() {
        return repo.findAll();
    }

    @Override
    public ClienteMongo actualizarCliente(ClienteMongo c) {
        return repo.save(c);
    }

    @Override
    public void eliminarCliente(String id) {
        repo.deleteById(id);
        
    }

    @Override
    public ClienteMongo buscarClientePorMySqlId(Integer id) {
        return repo.findByMySqlId(id);
    }
    
}
