package com.edix.cookbook.models;

import javax.persistence.*;

@Entity
@Table(name = "planes")
public class Plan {
    @Id
    @Column(name = "id_plan", nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "duracion")
    private Integer duracion;

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

    public Integer getDuracion() { return duracion; }

    public void setDuracion(Integer duracion) { this.duracion = duracion; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() { return "http://localhost:8080/planes/" + imagen;}

    public void setImagen(String imagen) {this.imagen = imagen; }
}