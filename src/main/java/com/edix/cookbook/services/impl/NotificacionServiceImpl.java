package com.edix.cookbook.services.impl;

import com.edix.cookbook.models.Notificacion;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.NotificacionesRepository;
import com.edix.cookbook.services.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class NotificacionServiceImpl implements INotificacionService {

    @Autowired
    NotificacionesRepository nRepo;

    @Autowired
    UsuarioServiceImpl uService;

    @Override
    public List<Notificacion> findByIdUsuario(int idUsuario) {
        Usuario aux = uService.findById(idUsuario);
        try {
            return nRepo.findByUsuario_IdUsuario(aux.getIdUsuario());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Notificacion> findAll() {
        return nRepo.findAll();
    }

    @Override
    public Notificacion crearNotificacion(Notificacion notificacion) {
        //Al guardar una notificacion, si tiene la fecha como null, se le asigna la fecha actual
        if (notificacion.getFechaHora() == null) {
            notificacion.setFechaHora(new Timestamp(new Date().getTime()));
        }
        return nRepo.save(notificacion);
    }

    @Override
    public Notificacion marcarNotificacionComoLeida(int idNotificacion) {
        Notificacion notificacion = nRepo.findById(idNotificacion).orElse(null);
        notificacion.setLeida(true);
        return nRepo.save(notificacion);
    }
}
