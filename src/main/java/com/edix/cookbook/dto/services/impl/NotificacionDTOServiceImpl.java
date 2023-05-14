package com.edix.cookbook.dto.services.impl;

import com.edix.cookbook.dto.model.NotificacionDTO;
import com.edix.cookbook.dto.services.INotificacionDTOService;
import com.edix.cookbook.models.Notificacion;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.NotificacionesRepository;
import com.edix.cookbook.services.IUsuarioService;
import com.edix.cookbook.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionDTOServiceImpl implements INotificacionDTOService {
        @Autowired
        private NotificacionesRepository nRepo;
        @Autowired
        private IUsuarioService uService;
        public Notificacion convertirDTOaEntidad(NotificacionDTO notificacionDTO) {
            Usuario usuario = uService.findById(notificacionDTO.getIdUsuario());
            Notificacion notificacion = new Notificacion();
            notificacion.setId(notificacionDTO.getId());
            notificacion.setUsuario(usuario);
            notificacion.setMensaje(notificacionDTO.getMensaje());
            notificacion.setFechaHora(notificacionDTO.getFechaHora());
            notificacion.setLeida(notificacionDTO.getLeida());

            return nRepo.save(notificacion);
        }

        public NotificacionDTO convertirEntidadADTO(Notificacion notificacion) {
            NotificacionDTO notificacionDTO = new NotificacionDTO();
            notificacionDTO.setId(notificacion.getId());
            notificacionDTO.setIdUsuario(notificacion.getUsuario().getIdUsuario());
            notificacionDTO.setMensaje(notificacion.getMensaje());
            notificacionDTO.setFechaHora(notificacion.getFechaHora());
            notificacionDTO.setLeida(notificacion.getLeida());

            return notificacionDTO;
        }
}
