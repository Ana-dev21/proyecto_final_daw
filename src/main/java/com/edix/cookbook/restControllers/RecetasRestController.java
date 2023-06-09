package com.edix.cookbook.restControllers;



import java.util.List;
import com.edix.cookbook.models.Comentario;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.ComentarioRepository;
import com.edix.cookbook.services.*;
import com.edix.cookbook.services.impl.ComentarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.services.ICategoriaService;
import com.edix.cookbook.services.IIngredienteService;
import com.edix.cookbook.services.IRecetaService;
import com.edix.cookbook.services.IRecetasConIngredienteService;
import com.edix.cookbook.services.IRecetasEnCategoriaService;
import com.edix.cookbook.services.IRecetasEnPlanesService;

@RestController
@RequestMapping("/recetas")
@CrossOrigin("*")
public class RecetasRestController {
	
	@Autowired ICategoriaService caService;
	@Autowired IRecetaService reService;
	@Autowired IIngredienteService inService;
	@Autowired IRecetasEnCategoriaService reCaService;
	@Autowired IRecetasConIngredienteService reCiService;
	@Autowired IRecetasEnPlanesService rePlService;
	@Autowired ComentarioServiceImpl coService;
	@Autowired IUsuarioService usService;
	
	
	/**
	 * Obtiene una receta
	 *
	 * @param idReceta El id de la receta
	 * @return ResponseEntity con la receta 
	 * @throws Exception
	 */
	@GetMapping("/una")
	public ResponseEntity<?> getRecetaById (@RequestParam int idReceta ) {
		try {
			return ResponseEntity.ok(reService.findById(idReceta));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener la receta", e);
		}
	}
	
	/**
	 * Guarda una imagen
	 * 
	 * @param idReceta
	 * @param imagen La imagen puede tener 5MB como máximo
	 * @return Receta La receta con el nombre de la imagen guardada en la propiedad de tipo String imagen
	 * @throws Exception Si ocurre algún error al guardar la imagen
	 */
	@PostMapping("/guardarImagen")
	// añadir parametros opcionales MultiFile
	public ResponseEntity<?> guardarImagen(@RequestParam int idReceta, @RequestParam("imagen") MultipartFile imagen) {
		try {
			return new ResponseEntity<>(reService.saveImage(idReceta, imagen), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al guardar imagen" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Crea una nueva receta
	 * 
	 * @param receta La receta a crear.
	 * @param file   (opcional) Archivo de imagen asociado a la receta.
	 * @return ResponseEntity con el resultado de la creación de la receta.
	 */
	@PostMapping("/alta")
	public ResponseEntity<?> createReceta(@RequestBody Receta receta, @RequestParam(name="imagen", required=false) MultipartFile file) {
		try {
			if (file != null) {
				return new ResponseEntity<>(reService.create(receta, file), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(reService.create(receta), HttpStatus.CREATED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear la receta" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Actualiza una receta existente.
	 * 
	 * @param receta La receta a actualizar.
	 * @return ResponseEntity con el resultado de la actualización de la receta.
	 */
	@PutMapping("/update")
    public ResponseEntity<?> updateReceta(@RequestBody Receta receta) {
        try {
			reService.update(receta);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al actualizar la receta" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	/**
	 * Obtiene todas las recetas que contengan una determinada categoría
	 *
	 * @param categoria La categoría por la que se filtrarán las recetas
	 * @return ResponseEntity con una lista de las recetas que contengan la categoría proporcionada
	 */
	@GetMapping("/porCategoria")
	public ResponseEntity<?> getRecetasByCategoria (@RequestParam String categoria){
		try {
			return ResponseEntity.ok(reCaService.findAllByCategoriaContaining(categoria));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener las recetas por categoria", e);
		}
	}

	/**
	 * Obtiene todas las recetas que contengan una determinada categoría
	 *
	 * @param idCategoria El id de la categoría por la que se filtrarán las recetas
	 * @return ResponseEntity con una lista de las recetas que contengan la categoría proporcionada
	 */
	@GetMapping("/porIdCategoria")
	public ResponseEntity<?> getRecetasByIdCategoria (@RequestParam int idCategoria){
		try {
			return ResponseEntity.ok(reCaService.findAllByCategoriaIdCategoria(idCategoria));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener las recetas por categoria", e);
		}
	}
	
	/**
	 * Obtiene todas las recetas que estén asociadas a un plan
	 *
	 * @param idPlan El id de Plan por el que se filtrarán las recetas
	 * @return ResponseEntity con una lista de las recetas con el Plan indicado
	 */
	@GetMapping("/porIdPlan")
	public ResponseEntity<?> getRecetasByIdPlan (@RequestParam int idPlan){
		try {
			return ResponseEntity.ok(rePlService.findAllByPlan(idPlan));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener las recetas por Plan", e);
		}
	}


	/**
	 * Obtiene todas las recetas que contengan un determinado ingrediente
	 *
	 * @param ingrediente El ingrediente por el que se filtrarán las recetas
	 * @return ResponseEntity con una lista de las recetas que contengan el ingrediente proporcionado
	 */
	@GetMapping("/porIngrediente")
	public ResponseEntity<?> getRecetasByIngrediente (@RequestParam String ingrediente){
		try {
			return ResponseEntity.ok(reCiService.findAllByIngrediente(ingrediente));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener las recetas por Ingrediente", e);
		}
	}
	
	/**
	 * Obtiene todas las recetas que contengan una lista de ingredientes
	 *
	 * @param listaIngredientes La lista de ingredientes por la que se filtrarán las recetas. ej. [2,3]
	 * @return ResponseEntity con una lista de las recetas que contengan los ingredientes proporcionados
	 */
	@PostMapping("/porIngredientes")
	public ResponseEntity<?> getRecetasByMultipleIngredientes (@RequestBody List<Integer> listaIngredientes){
		try {
			return ResponseEntity.ok(reCiService.findAllByMultipleIngredientes(listaIngredientes));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener las recetas por Ingredientes", e);
		}
	}
	
	/**
	 * Obtiene una lista de comentarios para una receta específica.
	 * 
	 * @param idReceta El ID de la receta.
	 * @return Lista de objetos Comentario que representan los comentarios de la receta.
	 */
	@GetMapping("/comentarios")
	public List<Comentario> ObtenerComentarios(@RequestParam int idReceta){
		try {
			return reService.listarComentarios(idReceta);
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener comentarios", e);
		}
	}

	/**
	 * Crea un nuevo comentario para una receta específica.
	 * 
	 * @param comentario El objeto Comentario que se va a crear.
	 * @param idReceta   El ID de la receta a la que se va a agregar el comentario.
	 * @param idUsuario  El ID del usuario que realiza el comentario.
	 * @return ResponseEntity con el resultado de la creación del comentario.
 	 */
	@PostMapping("/alta/comentario")
	public ResponseEntity<?> createComentario(@RequestBody Comentario comentario,
									   @RequestParam int idReceta,
									   @RequestParam int idUsuario) {
		try {
			Receta receta = reService.findById(idReceta);
			Usuario user = usService.findById(idUsuario);
			if (receta != null && user != null) {
				Comentario nuevoComentario = new Comentario();
				nuevoComentario.setReceta(receta);
				nuevoComentario.setUsuario(user);
				nuevoComentario.setTextoComentario(comentario.getTextoComentario());;
				coService.save(nuevoComentario);
				return new ResponseEntity<>(nuevoComentario, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al crear el comentario :" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	/**
	 * Elimina una receta por su ID.
	 *
	 * @param idReceta El ID de la receta a eliminar.
	 */
	@DeleteMapping("/eliminar")
	public void eliminarReceta (@RequestParam int idReceta){
		try {
			reService.deleteById(idReceta);
		}catch (Exception e) {
			throw new RuntimeException("Error al obtener comentarios", e);
		}
	}

	/**
	 * Obtiene todas las recetas creadas por un autor específico.
	 *
	 * @param idUsuario El ID del usuario autor.
	 * @return ResponseEntity con la lista de recetas creadas por el autor.
	 */
	@GetMapping("/porAutor")
	public ResponseEntity<?> getRecetasByAutor (@RequestParam int idUsuario){
		try {
			return ResponseEntity.ok(reService.findAllByUsuario_IdUsuario(idUsuario));
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener recetas por autor", e);
		}
	}
}
