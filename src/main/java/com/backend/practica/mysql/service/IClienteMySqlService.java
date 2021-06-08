package com.backend.practica.mysql.service;

import java.util.List;

import com.backend.practica.mysql.model.ClienteMySql;

public interface IClienteMySqlService {
    
    ClienteMySql crearCliente(ClienteMySql c);
    ClienteMySql buscarCliente(Integer id);
    List<ClienteMySql> buscarClientes();
    ClienteMySql actualizarCliente(ClienteMySql c);
    void eliminarCliente(Integer id);
    ClienteMySql buscarClientePorIdentificacion(String t, String n);
    List<ClienteMySql> buscarClientesMayoresOIgualA(short e);

}
