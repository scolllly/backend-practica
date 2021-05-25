package com.backend.practica.mysql.controller;


import java.util.List;

import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Digits;

import com.backend.practica.mysql.service.IClienteService;
import com.backend.practica.mysql.model.Cliente;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@Validated
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

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarClienteCondicion(@RequestParam(name = "tipoIdentificacion", required = false) String t, 
                                                    @RequestParam(name = "nmroIdentificacion", required = false) String n,
                                                    @RequestParam(name = "edad", required = false, defaultValue = "0") @Digits(integer = 3,fraction = 0) short e)
                                                        
    {
        if(t != null && n != null)
        {
            Cliente obj = service.buscarClientePorIdentificacion(t,n);
            if(obj.getId() == null){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
        }
        else if(e > 0)
        {
            List<Cliente> obj =  service.buscarClientesMayoresOIgualA(e);
            if(obj.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<List<Cliente>>(obj, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
        
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
