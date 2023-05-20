package com.edix.cookbook.services;

import com.edix.cookbook.models.Notificacion;

import java.util.List;

public interface INotificacionService {

    List<Notificacion> findByIdUsuario(int idUsuario);

    List<Notificacion> findAll();
    Notificacion crearNotificacion(Notificacion notificacion);
    Notificacion marcarNotificacionComoLeida(int idNotificacion);
    Notificacion actualizarNotificacion(Notificacion notificacion);
    boolean eliminarNotificacion(int idNotificacion);
    Notificacion findById(int idNotificacion);
}
