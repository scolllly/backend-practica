package com.backend.practica.mongo.service;

import java.util.List;

import com.backend.practica.mongo.model.ClienteMongo;

public interface IClienteMongoService {

    ClienteMongo crearCliente(ClienteMongo c);
    ClienteMongo buscarCliente(String id);
    List<ClienteMongo> buscarClientes();
    ClienteMongo actualizarCliente(ClienteMongo c);
    void eliminarCliente(String id);
    ClienteMongo buscarClientePorMySqlId(Integer id);
    
}
