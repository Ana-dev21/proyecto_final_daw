package com.edix.cookbook.restControllers;

import java.util.List;
import java.util.Optional;

import com.edix.cookbook.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetaService;
import com.edix.cookbook.services.IRecetasConIngredienteService;
import com.edix.cookbook.services.IRecetasEnCategoriaService;

@RestController
@RequestMapping("/recetas")
@CrossOrigin("*")
public class RecetasRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;
	@Autowired IIngredienteService inService;
	@Autowired IRecetasEnCategoriaService reCaService;
	@Autowired IRecetasConIngredienteService reCiService;
	
	/**
	 * Este método obtiene una receta
	 *
	 * @param idReceta El id de la receta
	 * @return ResponseEntity con la receta o mensaje de error si no la encuentra
	 * @throws Exception
	 */
	@GetMapping("/una")
	public ResponseEntity<Optional> getRecetaById (@RequestParam int idReceta ) {
		try {
			return ResponseEntity.ok(reService.findById(idReceta));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Este método obtiene todas las recetas que contengan una determinada categoría
	 *
	 * @param categoria La categoría por la que se filtrarán las recetas
	 * @return ResponseEntity con una lista de las recetas que contengan la categoría proporcionada
	 */
	@GetMapping("/porCategoria")
	public ResponseEntity<?> getRecetasByCategoria (@RequestParam String categoria){
		return ResponseEntity.ok(reCaService.findAllByCategoriaContaining(categoria));
	}
	
	/**
	 * Este método obtiene todas las recetas que contengan un determinado ingrediente
	 *
	 * @param ingrediente El ingrediente por el que se filtrarán las recetas
	 * @return ResponseEntity con una lista de las recetas que contengan el ingrediente proporcionado
	 */
	@GetMapping("/porIngrediente")
	public ResponseEntity<?> getRecetasByIngrediente (@RequestParam String ingrediente){
		return ResponseEntity.ok(reCiService.findAllByIngrediente(ingrediente));
	}
	
	/**
	 * Este método obtiene todas las recetas que contengan una lista de ingredientes
	 *
	 * @param listaIngredientes La lista de ingredientes por la que se filtrarán las recetas. ej. [2,3]
	 * @return ResponseEntity con una lista de las recetas que contengan los ingredientes proporcionados
	 */
	@PostMapping("/porIngredientes")
	public ResponseEntity<?> getRecetasByMultipleIngredientes (@RequestBody List<Integer> listaIngredientes){
		return ResponseEntity.ok(reCiService.findAllByMultipleIngredientes(listaIngredientes));
	}

	@GetMapping("/comentarios")
	public List<Comentario> ObtenerComentarios(@RequestParam int idReceta){
		return reService.listarComentarios(idReceta);
	}
	
	
}
