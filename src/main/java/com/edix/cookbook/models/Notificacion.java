package com.edix.cookbook.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "NOTIFICACIONES")
public class Notificacion {
    @Id
    @Column(name = "ID_NOTIFICACION", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario idUsuario;

    @Column(name = "MENSAJE")
    private String mensaje;

    @Column(name = "FECHA_HORA")
    private Instant fechaHora;

    @Column(name = "LEIDA")
    private Boolean leida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

}