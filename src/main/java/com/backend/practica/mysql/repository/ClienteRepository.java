package com.backend.practica.mysql.repository;

import java.util.List;
import com.backend.practica.mysql.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

    List<Cliente> findByEdadGreaterThanEqual(short e);
    Cliente findByTipoIdentificacionAndNmroIdentificacion(String t, String n);
    
}
