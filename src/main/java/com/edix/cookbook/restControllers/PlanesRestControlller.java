package com.edix.cookbook.restControllers;

import com.edix.cookbook.services.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planes")
@CrossOrigin("*")
public class PlanesRestControlller {

    @Autowired
    IPlanService plService;
    /**
     * Este método obtiene todos los planes
     * @return ResponseEntity con todos los planes
     * @throws Exception
     */
    @GetMapping("/todos")
    public ResponseEntity<?> getPlanes() {
        try {
            return ResponseEntity.ok(plService.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método obtiene un plan por su id
     * @param idPlan
     * @return ResponseEntity con el plan
     * @throws Exception
     */
    @GetMapping("/plan")
    public ResponseEntity<?> getPlanById(@RequestParam int idPlan) {
        try {
            return ResponseEntity.ok(plService.findById(idPlan));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
