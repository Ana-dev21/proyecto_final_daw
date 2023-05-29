package com.edix.cookbook.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the usuario_con_roles database table.
 * 
 */
@Entity
@Table(name="usuario_con_roles")
@NamedQuery(name="UsuarioConRoles.findAll", query="SELECT u FROM UsuarioConRoles u")
public class UsuarioConRoles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int id;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol role;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@JsonIgnore
	private Usuario usuario;

	public UsuarioConRoles() {
	}

	public UsuarioConRoles(Rol role, Usuario usuario) {
		super();
		this.role = role;
		this.usuario = usuario;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rol getRole() {
		return this.role;
	}

	public void setRole(Rol role) {
		this.role = role;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}