package com.edix.cookbook.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IRecetaService;

@RestController
@RequestMapping("/recetas")
public class RecetasRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;

	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias (){
		return ResponseEntity.ok(caService.findAll());
	}
	
	@GetMapping("/todas")
	public ResponseEntity<?> getAllRecetas (){
		return ResponseEntity.ok(reService.findAll());
	}
	
}
