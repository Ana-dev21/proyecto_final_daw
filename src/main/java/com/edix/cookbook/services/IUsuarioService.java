package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Usuario;

public interface IUsuarioService{
	List<Usuario> findAll();
	
	Usuario findById(int id);
	
	Usuario save(Usuario usuario);
	
	void deleteById(int id);

	Usuario login(String email, String password);
	
	Usuario findByEmail(String email);
	
	Usuario findByUsername(String username);

	Usuario registerNewUsuario(Usuario usuario) throws Exception;

}
