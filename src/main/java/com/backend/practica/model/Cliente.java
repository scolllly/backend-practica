package com.backend.practica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(name = "cliente", indexes = 
        {@Index(columnList = "edad", name = "cliente_edad"), 
        @Index(columnList = "tipo_identificacion, nmro_identificacion", name = "cliente_tipo_nmro_identificacion")
        }
    )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombres;
    private String apellidos;

    @Column(name = "tipo_identificacion", columnDefinition = "CHAR(20)")
    private String tipoIdentificacion;
    @Column(name = "nmro_identificacion", columnDefinition = "CHAR(20)")
    private String nmroIdentificacion;
    @Column(name = "edad", columnDefinition = "CHAR(3)")
    private short edad;
    // private String foto_ruta;


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

    
}
