package com.edix.cookbook.restControllers;

import java.util.List;
import java.util.Optional;

import com.edix.cookbook.models.Comentario;
import com.edix.cookbook.models.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetaService;
import com.edix.cookbook.services.IRecetasConIngredienteService;
import com.edix.cookbook.services.IRecetasEnCategoriaService;
@CrossOrigin("*")
@RestController
@RequestMapping("/recetas")
public class RecetasRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;
	@Autowired IIngredienteService inService;
	@Autowired IRecetasEnCategoriaService reCaService;
	@Autowired IRecetasConIngredienteService reCiService;

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
	
	@GetMapping("/porCategoria")
	public ResponseEntity<?> getRecetasByCategoria (@RequestParam String categoria){
		return ResponseEntity.ok(reCaService.findAllByCategoriaContaining(categoria));
	}
	
	@GetMapping("/porIngrediente")
	public ResponseEntity<?> getRecetasByIngrediente (@RequestParam String ingrediente){
		return ResponseEntity.ok(reCiService.findAllByIngrediente(ingrediente));
	}
	
	@PostMapping("/porIngredientes")
	public ResponseEntity<?> getRecetasByMultipleIngredientes (@RequestBody List<Integer> listaIngredientes){
		return ResponseEntity.ok(reCiService.findAllByMultipleIngredientes(listaIngredientes));
	}

	@GetMapping("/una")
	public Optional<Receta> obtenerUnaReceta(@RequestParam int idReceta){
		return reService.findById(idReceta);
	}

	@GetMapping("/comentarios")
	public List<Comentario> obetenerComentario(@RequestParam int idReceta){
		return reService.obtenerComentarios(idReceta);
	}




}
