package com.edix.cookbook.services;

import com.edix.cookbook.models.Notificacion;

import java.util.List;

public interface INotificacionService {

    List<Notificacion> findByIdUsuario(int idUsuario);

    Notificacion marcarNotificacionComoLeida(int idNotificacion);
}
