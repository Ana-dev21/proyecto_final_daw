package com.edix.cookbook.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the comentarios database table.
 * 
 */
@Entity
@Table(name="comentarios")
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMENTARIO")
	private int idComentario;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_PUBLICACION")
	private Date fechaPublicacion;

	@Lob
	@Column(name="TEXTO_COMENTARIO")
	private String textoComentario;

	//uni-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="ID_RECETA")
	private Receta receta;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public Comentario() {
	}

	public int getIdComentario() {
		return this.idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getTextoComentario() {
		return this.textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}