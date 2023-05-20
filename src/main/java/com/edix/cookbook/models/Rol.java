package com.edix.cookbook.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ROL")
	private int idRol;

	@Column(name="NOMBRE_ROL")
	private String nombreRol;

	//bi-directional many-to-one association to UsuarioConRoles
	@OneToMany(mappedBy="role")
	@JsonIgnore
	private List<UsuarioConRoles> usuarioConRoles;

	public Rol() {
	}

	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<UsuarioConRoles> getUsuarioConRoles() {
		return this.usuarioConRoles;
	}

	public void setUsuarioConRoles(List<UsuarioConRoles> usuarioConRoles) {
		this.usuarioConRoles = usuarioConRoles;
	}

	public UsuarioConRoles addUsuarioConRole(UsuarioConRoles usuarioConRole) {
		getUsuarioConRoles().add(usuarioConRole);
		usuarioConRole.setRole(this);

		return usuarioConRole;
	}

	public UsuarioConRoles removeUsuarioConRole(UsuarioConRoles usuarioConRole) {
		getUsuarioConRoles().remove(usuarioConRole);
		usuarioConRole.setRole(null);

		return usuarioConRole;
	}

}