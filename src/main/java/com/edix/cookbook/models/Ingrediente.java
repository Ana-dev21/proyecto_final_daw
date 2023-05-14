package com.edix.cookbook.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingredientes database table.
 * 
 */
@Entity
@Table(name="ingredientes")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingrediente")
	private int idIngrediente;

	@Lob
	private String descripcion;

	private String nombre;

	public Ingrediente() {
	}

	public int getIdIngrediente() {
		return this.idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}