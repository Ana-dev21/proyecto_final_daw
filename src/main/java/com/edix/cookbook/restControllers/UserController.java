package com.edix.cookbook.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.services.IUsuarioService;

import java.util.List;


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UserController {

	    @Autowired
	    private IUsuarioService uService;
	    
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
    
	    @GetMapping("/test")
	    public String test(){
	    	return "ok";
	    }

		@GetMapping("/todos")
		public List<Usuario> buscarTodos(){
			return uService.findAll();
		}

}

