package com.edix.cookbook.models;

import javax.persistence.*;

@Entity
@Table(name = "PLANES")
public class Plan {
    @Id
    @Column(name = "ID_PLAN", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}