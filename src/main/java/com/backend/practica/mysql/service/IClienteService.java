package com.backend.practica.mysql.service;

import java.util.List;

import com.backend.practica.mysql.model.Cliente;

public interface IClienteService {
    
    Cliente crearCliente(Cliente c);
    Cliente buscarCliente(Integer id);
    List<Cliente> buscarClientes();
    Cliente actualizarCliente(Cliente c);
    void eliminarCliente(Integer id);
    Cliente buscarClientePorIdentificacion(String t, String n);
    List<Cliente> buscarClientesMayoresOIgualA(short e);

}
