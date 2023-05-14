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
import java.util.stream.Collectors;

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
    public ResponseEntity<?> crearNotificacion(@RequestParam Notificacion notificacion) {
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

    @PostMapping("/crearDTO")
    public ResponseEntity<?> crearNotificacion(@RequestBody NotificacionDTO notificacionDTO) {
        Notificacion notificacion = nDTOService.convertirDTOaEntidad(notificacionDTO);
        // Realiza la lógica de creación de la notificación, por ejemplo, guardarla en la base de datos
        nService.crearNotificacion(notificacion);
        // Si la creación fue exitosa, puedes devolver una respuesta exitosa (código 200) o algún otro código apropiado
        return ResponseEntity.ok().build();
    }

}
