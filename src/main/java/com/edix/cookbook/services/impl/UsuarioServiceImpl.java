package com.edix.cookbook.services.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.edix.cookbook.models.Comentario;
import com.edix.cookbook.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.models.UsuarioConRoles;
import com.edix.cookbook.repository.RolRepository;
import com.edix.cookbook.repository.UsuarioRepository;
import com.edix.cookbook.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired UsuarioRepository uRepo;
	@Autowired ComentarioRepository coRepo;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired RolRepository rRepo;
	


	@Override
	public List<Usuario> findAll() {
		return uRepo.findAll();
	}

	@Override
	public Usuario findById(int id) {
		Optional<Usuario> usuarioOptional = uRepo.findById(id);
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró el usuario");
		}
	}

	@Override
	public Usuario save(Usuario usuario) {
		return null;
	}

	@Override
	public Usuario registerNewUsuario (Usuario usuario) throws Exception {
		if (!this.checkIfUserExists(usuario) && usuario != null) {
			Calendar cal = Calendar.getInstance();
			Usuario newUsuario = (Usuario) usuario.clone();
			newUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			List<UsuarioConRoles> ucroles = new ArrayList<UsuarioConRoles>();
			ucroles.add(new UsuarioConRoles(rRepo.findById(2).get(),newUsuario)); 
			newUsuario.setUsuarioConRoles(ucroles);
			newUsuario.setFechaRegistro(cal.getTime());
			return uRepo.save(newUsuario);
		}else
			throw new Exception ("El mail o username ya existe");
	}

	@Override
	public boolean deleteById(int id) {
		if (this.findById(id) != null) {
			uRepo.deleteById(id);
			return true;
		}else
			return false;
	}

	@Override
    public Usuario login(String email, String password) {
        Usuario usuario = this.findByEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }
	
	@Override
	public Usuario findByEmail(String email) {
		Usuario usuario = uRepo.findByEmail(email);
		if (usuario !=null) {		
			return usuario;
		}
		return null;
	}

	@Override
	public Usuario findByUsername(String username) {
		Usuario usuario = uRepo.findByUsername(username);
		return usuario;
	}
	
	private Boolean checkIfUserExists(Usuario usuario) {
		Usuario userSameEmail = this.findByEmail(usuario.getEmail());
		Usuario userSameUsername = this.findByUsername(usuario.getUsername());
		if (userSameEmail !=null && userSameUsername != null) {
			return true;
		}else
			return false;
	}

	@Override
	public Usuario saveImage(int idUsuario, MultipartFile imagen) throws Exception{
		Path directorioImagenes = Paths.get("src//main//resources//static/usuarios");
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
		Usuario usuario = this.findById(idUsuario);

		// recuperar bytes de la imagen recibida
		try {
			byte[] bytesImg = imagen.getBytes();
			
			// Guardar imagen en ruta
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" +imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);

			// Registrar ruta en atributo de imagen
			usuario.setImagen(imagen.getOriginalFilename());

			Usuario usuarioGuardado = this.update(usuario);
			
			return usuarioGuardado;
		} catch (Exception e) {
			throw new Exception(e);			
		}
		
	}

	@Override
	public boolean eliminarComentariosUsuario(int idUsuario) {
		List<Comentario> comentarios = uRepo.obtenerComentariosUsuario(idUsuario);
		if (comentarios != null) {
			for (Comentario comentario : comentarios) {
				coRepo.deleteById(comentario.getIdComentario());
			}
			return true;
		}else if (comentarios == null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) throws Exception {
		if (uRepo.findById(usuario.getIdUsuario()) != null) {
			return uRepo.save(usuario);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ha ocurrido un error al guardar la receta");
		}
	}

	@Override
	public Usuario actualizarPerfil(int idUsuario, String email, String username) throws Exception {
		Usuario usuario = this.findById(idUsuario);
		if (usuario != null) {
			usuario.setEmail(email);
			usuario.setUsername(username);
			return uRepo.save(usuario);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ha ocurrido un error al guardar la receta");
		}
	}

	@Override
	public String guardarImagenGoogle(int idUsuario, String urlImagen) throws Exception {
		Usuario usuario = this.findById(idUsuario);
		usuario.setImagen(urlImagen);
		uRepo.save(usuario);
		return urlImagen + " guardada con exito";
	}


	@Override
	public Usuario update(Usuario usuario) throws Exception {
	
				if (uRepo.findById(usuario.getIdUsuario()) != null) {
					return uRepo.save(usuario);
				}else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ha ocurrido un error al guardar la receta");
				}
	}


	@Override
	public Usuario update(Usuario usuario, MultipartFile imagen) throws Exception {
		//Guardar imagen
				if (uRepo.findById(usuario.getIdUsuario()) != null) {
					return uRepo.save(usuario);
				}else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ha ocurrido un error al guardar la receta");
				}
	}


}
