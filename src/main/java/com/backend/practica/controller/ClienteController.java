package com.backend.practica.controller;

import java.util.List;

import com.backend.practica.service.IClienteService;
import com.backend.practica.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cliente> creaCliente(@RequestBody Cliente c)
    {
        Cliente obj =  service.crearCliente(c);
        return new ResponseEntity<Cliente>(obj, HttpStatus.CREATED);        
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes()
    {
        List<Cliente> obj =  service.buscarClientes();
        if(obj.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<List<Cliente>>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable(name = "id") Integer id)
    {
        Cliente obj = service.buscarCliente(id);
        if(obj.getId() == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
    }   

    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente c) 
    {
        Cliente obj = service.actualizarCliente(c);
        if(obj.getId() == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable(name = "id") Integer id)
    {
        service.eliminarCliente(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);       
    }
    
}
