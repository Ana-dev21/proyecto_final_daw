package com.edix.cookbook.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetaService;

@RestController
@RequestMapping("/recetas")
@CrossOrigin("*")
public class MaestrosRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;
	@Autowired IIngredienteService inService;

	
	/**
	 * Obtiene todas las categorías de recetas existentes
	 *
	 * @return ResponseEntity con una lista de todas las categorías de recetas
	 */
	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias (){
		try {
			return ResponseEntity.ok(caService.findAll());
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener las categorias", e);
		}
	}
	
	/**
	 * Obtiene todas las recetas existentes
	 *
	 * @return ResponseEntity con una lista de todas las recetas
	 */
	@GetMapping("/todas")
	public ResponseEntity<?> getAllRecetas (){
		try {
			return ResponseEntity.ok(reService.findAll());
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener todas las recetas", e);
		}
	}
	
	/**
	 * Obtiene todos los ingredientes existentes
	 *
	 * @return ResponseEntity con una lista de todos los ingredientes
	 */
	@GetMapping("/ingredientes")
	public ResponseEntity<?> getAllIngredientes (){
		try {
			return ResponseEntity.ok(inService.findAll());
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener todos los ingredientes", e);
		}
	}
}
