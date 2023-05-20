package com.edix.cookbook.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario_con_roles")
public class UsuarioConRol {
    @EmbeddedId
    private UsuarioRolID id;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @MapsId("idRol")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public UsuarioRolID getId() {
        return id;
    }

    public void setId(UsuarioRolID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setIdRol(Rol rol) {
        this.rol = rol;
    }

}