package com.edix.cookbook.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetaService;

@RestController
@RequestMapping("/recetas")
public class MaestrosRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;
	@Autowired IIngredienteService inService;

	/**
	 * Este método obtiene todas las categorías de recetas existentes
	 *
	 * @return ResponseEntity con una lista de todas las categorías de recetas
	 */
	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias (){
		return ResponseEntity.ok(caService.findAll());
	}
	
	/**
	 * Este método obtiene todas las recetas existentes
	 *
	 * @return ResponseEntity con una lista de todas las recetas
	 */
	@GetMapping("/todas")
	public ResponseEntity<?> getAllRecetas (){
		return ResponseEntity.ok(reService.findAll());
	}
	
	/**
	 * Este método obtiene todos los ingredientes existentes
	 *
	 * @return ResponseEntity con una lista de todos los ingredientes
	 */
	@GetMapping("/ingredientes")
	public ResponseEntity<?> getAllIngredientes (){
		return ResponseEntity.ok(inService.findAll());
	}
}