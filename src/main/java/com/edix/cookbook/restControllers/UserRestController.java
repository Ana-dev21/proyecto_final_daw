package com.edix.cookbook.restControllers;

import com.edix.cookbook.models.Notificacion;
import com.edix.cookbook.models.UsuarioConPlan;
import com.edix.cookbook.services.IComentarioService;
import com.edix.cookbook.services.INotificacionService;
import com.edix.cookbook.services.IUsuarioConPLanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UserRestController {

	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IUsuarioConPLanService uConPlanService;
	@Autowired
	private INotificacionService nService;
	@Autowired
	private IComentarioService coService;
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

//    /**
//    * Método para validar el login de usuario
//    * @param Usuario usuario a validar
//    * @return Usuario si existe, Mensaje de error si no existe
//    */
//   @PostMapping("/loginValidation")
//   public ResponseEntity<Object> loginValidation(@RequestBody Usuario usuario) {
//       try {
//           Usuario usuarioAutenticado = uService.login(usuario.getEmail(), usuario.getPassword());
//           if (usuarioAutenticado != null) {
//               return ResponseEntity.ok(usuarioAutenticado);
//           }
//           return new ResponseEntity<>("El usuario o password indicado es incorrecto", HttpStatus.NOT_FOUND);
//       } catch (Exception ex) {
//           return new ResponseEntity<>("Ocurrió un error al procesar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);
//       }
//   }
	
    @PostMapping("/login")
    public ResponseEntity<?> loginApplication(@RequestBody AccountCredentials accountCredentials, HttpServletResponse rs) {
    	
    	Usuario user = null;
    	
    	try {
    		
    		user = uService.findByEmail(accountCredentials.getEmail());
    		if (user != null) {
    			List<GrantedAuthority> roles = user.getUsuarioConRoles().stream()
        				.map(uc -> new SimpleGrantedAuthority(uc.getRole().getNombreRol())).collect(Collectors.toList());
        	    authentication(accountCredentials.getEmail(),accountCredentials.getPassword(),roles, rs);
    		}else {
    			throw new AccessDeniedException("El usuario es incorrecto");
    		}	
			
		} catch (AccessDeniedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
        return ResponseEntity.ok().body(user);

    }

    /**
	 * Método para registrar un usuario nuevo.
	 * @param usuario a registrar
	 * @return	Usuario si se ha creado correctamente, mensaje de error si no se ha podido crear
	 */
    @PostMapping("/register")
    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario, HttpServletResponse rs) {
        try {
            Usuario usuarioRegistrado = uService.registerNewUsuario(usuario);
            List<GrantedAuthority> roles = usuarioRegistrado.getUsuarioConRoles().stream()
    				.map(uc -> new SimpleGrantedAuthority(uc.getRole().getNombreRol())).collect(Collectors.toList());
    	    
            authentication(usuario.getEmail(),usuario.getPassword(),roles, rs);
            
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

	@PostMapping("/guardarImagenGoogle")
	public ResponseEntity<?> guardarImagenGoogle(@RequestBody int idUsuario, @RequestBody String imagen){
		try {
			return new ResponseEntity<>(uService.guardarImagenGoogle(idUsuario, imagen), HttpStatus.CREATED);

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

	@GetMapping("/conPlan")
	public ResponseEntity<?> buscarUnPLan (@RequestParam int idUsuario ) {
		try {
			return ResponseEntity.ok(uConPlanService.findByIdUsuario(idUsuario));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/notificaciones")
	public ResponseEntity<?> buscarNotificaciones (@RequestParam int idUsuario ) {
		try {
			return ResponseEntity.ok(nService.findByIdUsuario(idUsuario));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PostMapping("/notificacion")
	public ResponseEntity<Notificacion> updateNotificacion(@RequestParam int idNotificacion) {
		Notificacion notificacion = nService.marcarNotificacionComoLeida(idNotificacion);
		if (notificacion != null) {
			return ResponseEntity.ok(notificacion);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/email")
	public Usuario buscarPorEmail(@RequestParam String email){
		return uService.findByEmail(email);
	}

	@DeleteMapping("/eliminar/comentarios")
	public ResponseEntity<?> eliminarComentarios(@RequestParam int idUsuario) {
		try {
			if (uService.eliminarComentariosUsuario(idUsuario)) {
				return ResponseEntity.ok(uService.findById(idUsuario));
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@DeleteMapping("/eliminar/comentario")
	public void eliminarComentario(@RequestParam int idComentario) {
		try{
			coService.deleteById(idComentario);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método para eliminar un usuario
	 * @param idUsuario id del usuario a eliminar
	 * @return ResponseEntity con el mensaje de éxito o error
	 */
	@DeleteMapping("/eliminar/uno")
	public ResponseEntity<String> eliminarUsuario(@RequestParam int idUsuario) {
		try {
			if (uService.deleteById(idUsuario)) {
				return new ResponseEntity<>( HttpStatus.OK);
			} else {
				return new ResponseEntity<>( HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al procesar la solicitud :" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<>(uService.updateUsuario(usuario), HttpStatus.OK);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PostMapping("/encrypt")
    public String encryptPassword(@RequestBody String password) {
        
        String encryptedPassword = passwordEncoder.encode(password);
        return encryptedPassword;
    }
    
    private void authentication(String email, String password, List<GrantedAuthority> roles, HttpServletResponse rs) throws IOException {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							email,
							password,
							roles
					)
			);
			TokenAuthenticationService.addAuthentication(rs, email, roles);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			throw new AccessDeniedException("El password es incorrecto");
		}
	}

		@PutMapping("/actualizarPerfil")
		public ResponseEntity<?> actualizarPerfil(@RequestParam int idUsuario,
		@RequestParam String email, @RequestParam String username) {
			try {
				return new ResponseEntity<>(uService.actualizarPerfil(idUsuario, email, username), HttpStatus.OK);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

