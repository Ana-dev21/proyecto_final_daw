package com.edix.cookbook.model.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the recetas database table.
 * 
 */
@Entity
@Table(name="recetas")
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECETA")
	private int idReceta;

	private BigDecimal calorias;

	private BigDecimal carbohidratos;

	@Lob
	private String descripcion;

	private BigDecimal grasas;

	private String imagen;

	@Lob
	private String instrucciones;

	private String nombre;

	private BigDecimal proteinas;

	@Column(name="TIEMPO_COCCION")
	private int tiempoCoccion;

	@Column(name="TIEMPO_PREPARACION")
	private int tiempoPreparacion;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public Receta() {
	}

	public int getIdReceta() {
		return this.idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public BigDecimal getCalorias() {
		return this.calorias;
	}

	public void setCalorias(BigDecimal calorias) {
		this.calorias = calorias;
	}

	public BigDecimal getCarbohidratos() {
		return this.carbohidratos;
	}

	public void setCarbohidratos(BigDecimal carbohidratos) {
		this.carbohidratos = carbohidratos;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getGrasas() {
		return this.grasas;
	}

	public void setGrasas(BigDecimal grasas) {
		this.grasas = grasas;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getInstrucciones() {
		return this.instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getProteinas() {
		return this.proteinas;
	}

	public void setProteinas(BigDecimal proteinas) {
		this.proteinas = proteinas;
	}

	public int getTiempoCoccion() {
		return this.tiempoCoccion;
	}

	public void setTiempoCoccion(int tiempoCoccion) {
		this.tiempoCoccion = tiempoCoccion;
	}

	public int getTiempoPreparacion() {
		return this.tiempoPreparacion;
	}

	public void setTiempoPreparacion(int tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}