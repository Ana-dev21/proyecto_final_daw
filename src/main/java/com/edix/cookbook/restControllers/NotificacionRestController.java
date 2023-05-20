package com.edix.cookbook.restControllers;

import com.edix.cookbook.dto.model.NotificacionDTO;
import com.edix.cookbook.dto.services.INotificacionDTOService;
import com.edix.cookbook.models.Notificacion;
import com.edix.cookbook.services.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin("*")
public class NotificacionRestController {

    @Autowired
    INotificacionService nService;

    @Autowired
    INotificacionDTOService nDTOService;

    /**
     * Este método permite crear una notificación
     * @param notificacion
     * @return ResponseEntity con la notificación creada
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearNotificacion(@RequestBody Notificacion notificacion) {
        System.out.println("Notificacion: " + notificacion.toString());
        try {
            return ResponseEntity.ok(nService.crearNotificacion(notificacion));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/todasDTO")
    public List<NotificacionDTO> getNotificaciones() {
        List<Notificacion> notificaciones = nService.findAll();
        List<NotificacionDTO> notificacionesDTO = new ArrayList<>();

        for (Notificacion notificacion : notificaciones) {
            notificacionesDTO.add(nDTOService.convertirEntidadADTO(notificacion));
        }

        return notificacionesDTO;
    }

    /**
     * Este método actualiza una notificación, recibe un DTO lo convierte y actualiza la notificación
     * @param notificacionDTO
     * @return ResponseEntity con la notificación actualizada
     * @throws Exception
     */
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarNotificacion(@RequestBody NotificacionDTO notificacionDTO) throws Exception {
        try {
            Notificacion notificacion = nDTOService.convertirDTOaEntidad(notificacionDTO);
            return ResponseEntity.ok(nService.actualizarNotificacion(notificacion));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método elimina una notificación
     * @param idNotificacion
     * @return ResponseEntity con un mensaje de confirmación
     * @throws Exception
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarNotificacion(@RequestParam int idNotificacion) throws Exception {
        try {
            return ResponseEntity.ok(nService.eliminarNotificacion(idNotificacion));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Este método obtiene una notificaciónDTO por su id
     * @param idNotificacion
     * @return ResponseEntity con la notificaciónDTO
     * @throws Exception
     */
    @GetMapping("/una")
    public ResponseEntity<?> getNotificacionById(@RequestParam int idNotificacion) throws Exception {
        try {
            return ResponseEntity.ok(nDTOService.convertirEntidadADTO(nService.findById(idNotificacion)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
