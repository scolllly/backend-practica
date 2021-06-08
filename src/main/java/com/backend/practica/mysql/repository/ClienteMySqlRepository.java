package com.backend.practica.mysql.repository;

import java.util.List;
import com.backend.practica.mysql.model.ClienteMySql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteMySqlRepository extends JpaRepository<ClienteMySql,Integer>{

    List<ClienteMySql> findByEdadGreaterThanEqual(short e);
    ClienteMySql findByTipoIdentificacionAndNmroIdentificacion(String t, String n);
    
}
