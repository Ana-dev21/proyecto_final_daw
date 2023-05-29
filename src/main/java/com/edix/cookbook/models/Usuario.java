package com.edix.cookbook.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	private String imagen;

	private String password;

	private String username;
	
	//bi-directional many-to-one association to UsuarioConRoles
	@OneToMany(mappedBy="usuario")
	private List<UsuarioConRoles> usuarioConRoles;

	@ManyToOne
	@JoinColumn(name = "id_plan")
	private Plan plan;


	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getImagen() {
		return "http://localhost:8080/usuarios/" + imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
    @JsonIgnore
	public String getPassword() {
		return this.password;
	}
    @JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	public List<UsuarioConRoles> getUsuarioConRoles() {
		return this.usuarioConRoles;
	}

	public void setUsuarioConRoles(List<UsuarioConRoles> usuarioConRoles) {
		this.usuarioConRoles = usuarioConRoles;
	}

	public UsuarioConRoles addUsuarioConRole(UsuarioConRoles usuarioConRole) {
		getUsuarioConRoles().add(usuarioConRole);
		usuarioConRole.setUsuario(this);

		return usuarioConRole;
	}

	public UsuarioConRoles removeUsuarioConRole(UsuarioConRoles usuarioConRole) {
		getUsuarioConRoles().remove(usuarioConRole);
		usuarioConRole.setUsuario( null);

		return usuarioConRole;
	}
	
	 @Override
	public Object clone() throws CloneNotSupportedException {
	  
		 Usuario nuevo = new Usuario ();
		 nuevo.setUsername(this.username);
		 nuevo.setPassword(this.password);
		 nuevo.setEmail(this.email);
		 
	    return nuevo;
	  }

}