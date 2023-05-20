package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Comentario;
import org.springframework.web.multipart.MultipartFile;

import com.edix.cookbook.models.Usuario;

public interface IUsuarioService{
	List<Usuario> findAll();
	
	Usuario findById(int id);
	
	Usuario save(Usuario usuario);
	
	Usuario update(Usuario usuario) throws Exception;
	
	Usuario update(Usuario usuario, MultipartFile imagen) throws Exception;
	
	void deleteById(int id);

	Usuario login(String email, String password);
	
	Usuario findByEmail(String email);
	
	Usuario findByUsername(String username);

	Usuario registerNewUsuario(Usuario usuario) throws Exception;

	Usuario saveImage(int idUsuario, MultipartFile imagen) throws Exception;

	void eliminarComentariosUsuario(int idUsuario);

	

}
