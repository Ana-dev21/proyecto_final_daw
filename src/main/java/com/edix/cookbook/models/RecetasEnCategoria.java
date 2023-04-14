package com.edix.cookbook.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recetas_en_categorias database table.
 * 
 */
@Entity
@Table(name="recetas_en_categorias")
@NamedQuery(name="RecetasEnCategoria.findAll", query="SELECT r FROM RecetasEnCategoria r")
public class RecetasEnCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECETA_CATEGORIA")
	private int idRecetaCategoria;

	//uni-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIA")
	private Categoria categoria;

	//uni-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="ID_RECETA")
	private Receta receta;

	public RecetasEnCategoria() {
	}

	public int getIdRecetaCategoria() {
		return this.idRecetaCategoria;
	}

	public void setIdRecetaCategoria(int idRecetaCategoria) {
		this.idRecetaCategoria = idRecetaCategoria;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}