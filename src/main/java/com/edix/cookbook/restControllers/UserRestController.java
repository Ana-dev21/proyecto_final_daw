package com.edix.cookbook.restControllers;

import com.edix.cookbook.services.IComentarioService;
import com.edix.cookbook.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.services.IUsuarioService;

import java.util.List;


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UserRestController {

	    @Autowired
	    private IUsuarioService uService;
		@Autowired IComentarioService coService;


	    /**
	     * Este método obtiene un usuario
	     * 
	     * @param idUsuario
	     * @return ResponseEntity con el Usuario 
	     * @throws Exception si no encuentra el usuario o el idUsuario es nulo
	     */
		@GetMapping("/uno")
		public ResponseEntity<?> getUserById (@RequestParam int idUsuario ) {
			try {
				return ResponseEntity.ok(uService.findById(idUsuario));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	    
	    /**
	     * Método para validar el login de usuario
	     * @param Usuario usuario a validar
	     * @return Usuario si existe, Mensaje de error si no existe
	     */
	    @PostMapping("/loginValidation")
	    public ResponseEntity<Object> loginValidation(@RequestBody Usuario usuario) {
	        try {
	            Usuario usuarioAutenticado = uService.login(usuario.getEmail(), usuario.getPassword());
	            if (usuarioAutenticado != null) {
	                return ResponseEntity.ok(usuarioAutenticado);
	            }
	            return new ResponseEntity<>("El usuario o password indicado es incorrecto", HttpStatus.NOT_FOUND);
	        } catch (Exception ex) {
	            return new ResponseEntity<>("Ocurrió un error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    /**
		 * Método para registrar un usuario nuevo.
		 * @param Usuario usuario a registrar
		 * @return	Usuario si se ha creado correctamente, mensaje de error si no se ha podido crear
		 */
	    @PostMapping("/register")
	    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario) {
	        try {
	            Usuario usuarioRegistrado = uService.registerNewUsuario(usuario);
	            return ResponseEntity.ok(usuarioRegistrado);
	        } catch (Exception ex) {
	            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    @PostMapping("/guardarImagen")
		// añadir parametros opcionales MultiFile
		public ResponseEntity<?> guardarImagen(@RequestParam int idUsuario, @RequestParam("imagen") MultipartFile imagen) {
			try {
				return new ResponseEntity<>(uService.saveImage(idUsuario, imagen), HttpStatus.CREATED);

			} catch (Exception e) {
				return new ResponseEntity<>("Ocurrió un error al procesar la solicitud :" + e.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
    
	    @GetMapping("/test")
	    public String test(){
	    	return "ok";
	    }

		@GetMapping("/todos")
		public List<Usuario> buscarTodos(){
			return uService.findAll();
		}

		@GetMapping("/email")
		public Usuario buscarPorEmail(@RequestParam String email){
			return uService.findByEmail(email);
		}

		@DeleteMapping("/eliminar")
		public String eliminarUsuario(@RequestParam int idUsuario) {
			try {
					uService.deleteById(idUsuario);
					return ("Usuario eliminado correctamente");

			} catch (Exception e) {
				return "Ocurrió un error al procesar la solicitud :" + e.getMessage();
			}
		}

		@DeleteMapping("/eliminar/comentarios")
		public String borrarTodosAsociados(@RequestParam int idUsuario) {
			try {
				if (uService.findById(idUsuario) != null) {
					uService.eliminarComentariosUsuario(idUsuario);
					return "Comentarios eliminados";
				}
			} catch (Exception e) {
				return "comentarios no eliminados";
			}
			return null;
		}

		@DeleteMapping("eliminar/uno")
		public String eliminarUnComentario(@RequestParam int idComentario){
			try {
				coService.deleteById(idComentario);
				return "Comentario eliminado";
			} catch (Exception e) {
				return "Comentario no eliminado";
			}
		}

}

