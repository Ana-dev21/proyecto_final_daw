package com.edix.cookbook.restControllers;

import com.edix.cookbook.models.RecetasConIngrediente;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetasConIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/ingredientes")
@CrossOrigin("*")
public class IngredientesRestController {

    @Autowired
    IIngredienteService iServ;
    @Autowired
    IRecetasConIngredienteService riServ;

    
    /**
     * Obtiene todas los ingredientes que contiene una receta, su cantidad y su medida
     * 
     * @param idReceta
     * @return ResponseEntity con todos los ingredientes de una receta
     * @throws Exception
     */
    @GetMapping("/porIdReceta")
    public ResponseEntity<?> getIngredientesByIdReceta(int idReceta) {
        try {
            return ResponseEntity.ok(riServ.findAllIngredientesByReceta(idReceta));
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar ingredientes", e);
        }
    }

    /**
     * Crea un nuevo insert en recetas con ingredientes
     * 
     * @param idReceta
     * @param idIngrediente
     * @param cantidad
     * @param unidadMedida
     * @return ResponseEntity con el nuevo insert
     * @throws Exception
     */
    @PostMapping("/nuevoIngredienteEnReceta")
    public ResponseEntity<?> nuevoIngredienteEnReceta(int idReceta, int idIngrediente, String unidadMedida, BigDecimal cantidad) {
        try {
            return new ResponseEntity<>(riServ.nuevoIngredienteEnReceta(idReceta, idIngrediente, unidadMedida, cantidad), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Error al añadir ingredientes a receta", e);
        }
    }

    /**
     * Crea un nuevo insert en la tabla Recetas con Ingredientes
     * 
     * @param recetasConIngredientes
     * @return ResponseEntity con el nuevo insert
     * @throws Exception
     */
    @PostMapping("/altaIngredienteEnReceta")
    public ResponseEntity<?> altaIngredienteEnReceta(@RequestBody RecetasConIngrediente recetasConIngredientes) {
        System.out.println("INGREDIENTE EN RECETA" + recetasConIngredientes.toString());
        try {
            return new ResponseEntity<>(riServ.save(recetasConIngredientes), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al crear el ingrediente en receta :" + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
