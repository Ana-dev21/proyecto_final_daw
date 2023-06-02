package com.edix.cookbook.repository;

import com.edix.cookbook.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionesRepository extends JpaRepository<Notificacion,Integer> {

    List<Notificacion> findByUsuario_IdUsuario(int idUsuario);
}
