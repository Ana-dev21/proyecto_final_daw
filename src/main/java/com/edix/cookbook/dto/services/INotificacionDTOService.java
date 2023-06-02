package com.edix.cookbook.dto.services;

import com.edix.cookbook.dto.model.NotificacionDTO;
import com.edix.cookbook.models.Notificacion;
import com.edix.cookbook.repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface INotificacionDTOService {
	
        Notificacion convertirDTOaEntidad(NotificacionDTO notificacionDTO);

        NotificacionDTO convertirEntidadADTO(Notificacion notificacion);
}
