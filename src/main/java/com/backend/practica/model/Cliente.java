package com.backend.practica.model;

import javax.validation.constraints.NotNull;


public class Cliente {

    private Integer id;

    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    private String tipoIdentificacion;
    private String nmroIdentificacion;
    private short edad;

    private String foto;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNmroIdentificacion() {
        return this.nmroIdentificacion;
    }

    public void setNmroIdentificacion(String nmroIdentificacion) {
        this.nmroIdentificacion = nmroIdentificacion;
    }

    public short getEdad() {
        return this.edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    
}
