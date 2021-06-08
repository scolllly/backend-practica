package com.backend.practica.mongo.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
public class ClienteMongo {

    @Id
    @NotNull
    private String id;

    @NotNull
    private Integer mySqlId;

    @Null
    private String foto;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMySqlId() {
        return this.mySqlId;
    }

    public void setMySqlId(Integer mySqlId) {
        this.mySqlId = mySqlId;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    
    
}
