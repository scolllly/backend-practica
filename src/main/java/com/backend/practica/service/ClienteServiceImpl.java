package com.backend.practica.service;

import java.util.List;

import com.backend.practica.model.Cliente;
import com.backend.practica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public Cliente crearCliente(Cliente c) {
        return repo.save(c);
    }

    @Override
    public Cliente buscarCliente(Integer id) {
        return repo.findById(id).orElse(null);	
    }

    @Override
    public List<Cliente> buscarClientes() {
        return repo.findAll();
    }

    @Override
    public Cliente actualizarCliente(Cliente c) {
        return repo.save(c);
    }

    @Override
    public void eliminarCliente(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Cliente buscarClientePorIdentificacion(String t, String n) {
        return repo.findByTipoIdentificacionAndNmroIdentificacion(t,n);
    }

    @Override
    public List<Cliente> buscarClientesMayoresOIgualA(short e) {
        return repo.findByEdadGreaterThanEqual(e);
    }

    
}
