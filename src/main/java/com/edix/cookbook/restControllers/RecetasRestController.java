package com.edix.cookbook.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetaService;
import com.edix.cookbook.services.impl.IngredienteServiceImpl;

@RestController
@RequestMapping("/recetas")
public class RecetasRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;
	@Autowired IIngredienteService inService;

	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias (){
		return ResponseEntity.ok(caService.findAll());
	}
	
	@GetMapping("/todas")
	public ResponseEntity<?> getAllRecetas (){
		return ResponseEntity.ok(reService.findAll());
	}
	
	@GetMapping("/ingredientes")
	public ResponseEntity<?> getAllIngredientes (){
		return ResponseEntity.ok(inService.findAll());
	}
}
