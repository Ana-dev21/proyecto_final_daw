package com.edix.cookbook.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.edix.cookbook.config.AccountCredentials;
import com.edix.cookbook.config.TokenAuthenticationService;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.services.IUsuarioService;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UserRestController {

	    @Autowired
	    private IUsuarioService uService;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private AuthenticationManager authenticationManager;

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
	    
//	    /**
//	     * Método para validar el login de usuario
//	     * @param Usuario usuario a validar
//	     * @return Usuario si existe, Mensaje de error si no existe
//	     */
//	    @PostMapping("/loginValidation")
//	    public ResponseEntity<Object> loginValidation(@RequestBody Usuario usuario) {
//	        try {
//	            Usuario usuarioAutenticado = uService.login(usuario.getEmail(), usuario.getPassword());
//	            if (usuarioAutenticado != null) {
//	                return ResponseEntity.ok(usuarioAutenticado);
//	            }
//	            return new ResponseEntity<>("El usuario o password indicado es incorrecto", HttpStatus.NOT_FOUND);
//	        } catch (Exception ex) {
//	            return new ResponseEntity<>("Ocurrió un error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<Usuario> loginApplication(@RequestBody AccountCredentials accountCredentials, HttpServletResponse rs) {
	    	
	    	Usuario user = null;
	    	
	    	try {
	    		
	    	    authentication(accountCredentials.getEmail(),accountCredentials.getPassword(), rs);
	    		user = uService.findByEmail(accountCredentials.getEmail());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return ResponseEntity.ok().body(user);

	    }

		
	    
	    /**
		 * Método para registrar un usuario nuevo.
		 * @param Usuario usuario a registrar
		 * @return	Usuario si se ha creado correctamente, mensaje de error si no se ha podido crear
		 */
	    @PostMapping("/register")
	    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario, HttpServletResponse rs) {
	        try {
	            Usuario usuarioRegistrado = uService.registerNewUsuario(usuario);
	            authentication(usuario.getEmail(),usuario.getPassword(), rs);
	            
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
		
	    @PostMapping("/encrypt")
	    public String encryptPassword(@RequestBody String password) {
	        
	        String encryptedPassword = passwordEncoder.encode(password);
	        return encryptedPassword;
	    }
	    
	    private void authentication(String email, String password, HttpServletResponse rs) throws IOException {
			Authentication authentication = authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			        		email,
			        		password
			        )
			);
			TokenAuthenticationService.addAuthentication(rs, email);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
}

