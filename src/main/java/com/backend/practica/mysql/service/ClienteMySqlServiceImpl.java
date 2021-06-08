package com.backend.practica.mysql.service;


import java.util.List;

import com.backend.practica.mysql.model.ClienteMySql;
import com.backend.practica.mysql.repository.ClienteMySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteMySqlServiceImpl implements IClienteMySqlService {

    @Autowired
    private ClienteMySqlRepository repo;

    @Override
    public ClienteMySql crearCliente(ClienteMySql c) {
        return repo.save(c);
    }

    @Override
    public ClienteMySql buscarCliente(Integer id) {
        return repo.findById(id).orElse(null);	
    }

    @Override
    public List<ClienteMySql> buscarClientes() {
        return repo.findAll();
    }

    @Override
    public ClienteMySql actualizarCliente(ClienteMySql c) {
        return repo.save(c);
    }

    @Override
    public void eliminarCliente(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public ClienteMySql buscarClientePorIdentificacion(String t, String n) {
        return repo.findByTipoIdentificacionAndNmroIdentificacion(t,n);
    }

    @Override
    public List<ClienteMySql> buscarClientesMayoresOIgualA(short e) {
        return repo.findByEdadGreaterThanEqual(e);
    }

    
}
