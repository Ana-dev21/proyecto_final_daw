package com.edix.cookbook.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @Column(name = "id_notificacion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha_hora")
    private Timestamp fechaHora;

    @Column(name = "leida")
    private Boolean leida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", fechaHora=" + fechaHora +
                ", leida=" + leida +
                '}';
    }
}