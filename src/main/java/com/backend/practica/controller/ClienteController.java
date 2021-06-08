package com.backend.practica.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.backend.practica.mysql.service.IClienteMySqlService;
import com.backend.practica.model.Cliente;
import com.backend.practica.mongo.model.ClienteMongo;
import com.backend.practica.mongo.service.IClienteMongoService;
import com.backend.practica.mysql.model.ClienteMySql;

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
    private IClienteMySqlService service;

    @Autowired
    private IClienteMongoService service_mongo;


    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes()
    {
        List<Cliente> obj = new ArrayList<Cliente>();
        List<ClienteMySql> objmysql =  service.buscarClientes();
        if(objmysql.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        // Clientes Mysql crean cliente modelo
        objmysql.forEach(mysql ->
            {
                Cliente cl = new Cliente();
                cl.setId(mysql.getId());
                cl.setNombres(mysql.getNombres());
                cl.setApellidos(mysql.getApellidos());
                cl.setEdad(mysql.getEdad());
                cl.setTipoIdentificacion(mysql.getTipoIdentificacion());
                cl.setNmroIdentificacion(mysql.getNmroIdentificacion());

                // Llama a fotos de Cliente Mongo
                ClienteMongo mongo = service_mongo.buscarClientePorMySqlId(cl.getId());
                if(!Objects.isNull(mongo)){
                    cl.setFoto(service_mongo.buscarClientePorMySqlId(cl.getId()).getFoto());
                }
                
                obj.add(cl);
            }
        );
        return new ResponseEntity<List<Cliente>>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable(name = "id") Integer id)
    {
        ClienteMySql objmysql = service.buscarCliente(id);
        if(objmysql.getId() == null){
            return ResponseEntity.notFound().build();
        }
        Cliente obj = new Cliente();
        obj.setId(objmysql.getId());
        obj.setNombres(objmysql.getNombres());
        obj.setApellidos(objmysql.getApellidos());
        obj.setEdad(objmysql.getEdad());
        obj.setTipoIdentificacion(objmysql.getTipoIdentificacion());
        obj.setNmroIdentificacion(objmysql.getNmroIdentificacion());

        // Llama a fotos de Cliente Mongo
        ClienteMongo mongo = service_mongo.buscarClientePorMySqlId(obj.getId());
        if(!Objects.isNull(mongo)){
            obj.setFoto(service_mongo.buscarClientePorMySqlId(obj.getId()).getFoto());
        }

        return new ResponseEntity<Cliente>(obj, HttpStatus.OK);
    }   

    @GetMapping("buscar/identificacion/")
    public ResponseEntity<Cliente> buscarClientePorTipoIdentificacion(@RequestParam(name = "tipo", required = true)  String tipo,
                                                    @RequestParam(name = "identificacion",  required = true)  String identificacion,
                                                    @RequestParam(name = "edad")  short edad)
    {
       
        if(tipo == "" || identificacion == ""){
            return ResponseEntity.notFound().build();
        }

        ClienteMySql mysql = service.buscarClientePorIdentificacion(tipo, identificacion);
        if(mysql.getId() == null)
        {
            return ResponseEntity.notFound().build();    
        }
        Cliente cliente = new Cliente();
        cliente.setId(mysql.getId());
        cliente.setNombres(mysql.getNombres());
        cliente.setApellidos(mysql.getApellidos());
        cliente.setEdad(mysql.getEdad());
        cliente.setTipoIdentificacion(mysql.getTipoIdentificacion());
        cliente.setNmroIdentificacion(mysql.getNmroIdentificacion());

        // Mongo
        ClienteMongo mongo = service_mongo.buscarClientePorMySqlId(mysql.getId());
        if(!Objects.isNull(mongo)){
            cliente.setFoto(mongo.getFoto());
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    } 
    
    @GetMapping("buscar/edad/")
    public ResponseEntity<List<Cliente>> buscarClientePorEdad(@Min(value = 0) @RequestParam(name = "edad")  short edad)
    {
       
        List<ClienteMySql> mysqlList = service.buscarClientesMayoresOIgualA(edad);
        if(mysqlList.isEmpty())
        {
            return ResponseEntity.notFound().build();    
        }

        List<Cliente> clienteLista = new ArrayList<Cliente>();
        mysqlList.forEach(mysql -> {

            Cliente cliente = new Cliente();

            // Mysql
            cliente.setId(mysql.getId());
            cliente.setNombres(mysql.getNombres());
            cliente.setApellidos(mysql.getApellidos());
            cliente.setEdad(mysql.getEdad());
            cliente.setTipoIdentificacion(mysql.getTipoIdentificacion());
            cliente.setNmroIdentificacion(mysql.getNmroIdentificacion());

            // Mongo
            ClienteMongo mongo = service_mongo.buscarClientePorMySqlId(mysql.getId());
            if(!Objects.isNull(mongo)){
                cliente.setFoto(mongo.getFoto());
            }

            clienteLista.add(cliente);

        });

        return new ResponseEntity<List<Cliente>>(clienteLista, HttpStatus.OK);
    }  

    @PostMapping
    public ResponseEntity<Cliente> creaCliente(@Valid @RequestBody Cliente c)
    {
        // mysql
        ClienteMySql mysql = new ClienteMySql();
        mysql.setNombres(c.getNombres());
        mysql.setApellidos(c.getApellidos());
        mysql.setEdad(c.getEdad());
        mysql.setTipoIdentificacion(c.getTipoIdentificacion());
        mysql.setNmroIdentificacion(c.getNmroIdentificacion());
        mysql = service.crearCliente(mysql);

        // mongo
        ClienteMongo mongo = new ClienteMongo();
        if(c.getFoto() != null)
        {            
            mongo.setMySqlId(mysql.getId());
            mongo.setFoto(c.getFoto());
            mongo = service_mongo.crearCliente(mongo);
        }
        
        
        Cliente obj = new Cliente();
        obj.setId(mysql.getId());
        obj.setNombres(mysql.getNombres());
        obj.setApellidos(mysql.getApellidos());
        obj.setEdad(mysql.getEdad());
        obj.setTipoIdentificacion(mysql.getTipoIdentificacion());
        obj.setNmroIdentificacion(mysql.getNmroIdentificacion());
        obj.setFoto(mongo.getFoto());
        return new ResponseEntity<Cliente>(obj, HttpStatus.CREATED);        
    }

    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@Valid @RequestBody Cliente c)
    {
        if(c.getId() == null)
        {
            return ResponseEntity.notFound().build();    
        }

        ClienteMySql mysql = service.buscarCliente(c.getId());
        if(mysql.getId() == null)
        {
            return ResponseEntity.notFound().build();    
        }

        // mysql
        mysql.setNombres(c.getNombres());
        mysql.setApellidos(c.getApellidos());
        mysql.setEdad(c.getEdad());
        mysql.setTipoIdentificacion(c.getTipoIdentificacion());
        mysql.setNmroIdentificacion(c.getNmroIdentificacion());
        service.actualizarCliente(mysql);

        Cliente cliente = new Cliente();
        cliente.setId(mysql.getId());
        cliente.setNombres(mysql.getNombres());
        cliente.setApellidos(mysql.getApellidos());
        cliente.setEdad(mysql.getEdad());
        cliente.setTipoIdentificacion(mysql.getTipoIdentificacion());
        cliente.setNmroIdentificacion(mysql.getNmroIdentificacion());

        // mongo
        ClienteMongo mongo = service_mongo.buscarClientePorMySqlId(mysql.getId());
        if(!Objects.isNull(mongo)){

            mongo.setFoto(c.getFoto());
            mongo = service_mongo.actualizarCliente(mongo);
            cliente.setFoto(mongo.getFoto());
        }
        else
        {
            ClienteMongo mongoNuevo = new ClienteMongo();
            mongoNuevo.setMySqlId(mysql.getId());
            mongoNuevo.setFoto(c.getFoto());
            mongoNuevo = service_mongo.crearCliente(mongoNuevo);
            cliente.setFoto(mongoNuevo.getFoto());
        }

        
        
       
        
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable(name = "id") Integer id)
    {
        ClienteMySql mysql = service.buscarCliente(id);
        if(mysql.getId() == null){
            return ResponseEntity.notFound().build();
        }

        // mysql
        service.eliminarCliente(id);
        
        // mongo
        ClienteMongo mongo = service_mongo.buscarClientePorMySqlId(mysql.getId());
        if(!Objects.isNull(mongo)){
            service_mongo.eliminarCliente(mongo.getId());
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }   

    
    
}
