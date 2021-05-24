package com.backend.practica.controller;

import java.util.List;

import com.backend.practica.service.IClienteService;
import com.backend.practica.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @PostMapping
    public Cliente creaCliente(@RequestBody Cliente c)
    {
        return service.crearCliente(c);
    }

    @GetMapping
    public List<Cliente> buscarClientes()
    {
        return service.buscarClientes();
    }

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable(name = "id") Integer id)
    {
        return service.buscarCliente(id);
    }   

    @PutMapping
    public Cliente actualizarCliente(@RequestBody Cliente c) {
        return service.actualizarCliente(c);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable(name = "id") Integer id)
    {
        service.eliminarCliente(id);
    }
    
}
