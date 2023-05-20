package com.edix.cookbook.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class NotificacionDTO {
    private Integer idNotificacion;
    private Integer idUsuario;
    private String mensaje;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") // Ajusta el patrón según el formato esperado
    private Timestamp fechaHora;
    private Boolean leida;

    // Constructor vacío
    public NotificacionDTO() {
    }

    // Constructor con parámetros
    public NotificacionDTO(Integer idNotificacion, Integer idUsuario, String mensaje, Timestamp fechaHora, Boolean leida) {
        this.idNotificacion = idNotificacion;
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
        this.leida = leida;
    }

    // Getters y setters
    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
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
}

