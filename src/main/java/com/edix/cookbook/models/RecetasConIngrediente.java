package com.edix.cookbook.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;


/**
 * The persistent class for the recetas_con_ingredientes database table.
 * 
 */
@Entity
@Table(name="recetas_con_ingredientes")
@NamedQuery(name="RecetasConIngrediente.findAll", query="SELECT r FROM RecetasConIngrediente r")
public class RecetasConIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_receta_ingrediente")
	private int idRecetaIngrediente;

	private BigDecimal cantidad;

	@Column(name="unidad_medida")
	private String unidadMedida;

	//uni-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="id_ingrediente")
	private Ingrediente ingrediente;

	//uni-directional many-to-one association to Receta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_receta")
	@JsonBackReference
	private Receta receta;

	public RecetasConIngrediente() {
	}

	public int getIdRecetaIngrediente() {
		return this.idRecetaIngrediente;
	}

	public void setIdRecetaIngrediente(int idRecetaIngrediente) {
		this.idRecetaIngrediente = idRecetaIngrediente;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}