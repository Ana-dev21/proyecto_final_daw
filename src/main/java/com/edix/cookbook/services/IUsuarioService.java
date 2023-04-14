package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Usuario;

public interface IUsuarioService{
	List<Usuario> findAll();
	
	Usuario findById(int id);
	
	Usuario save(Usuario usuario);
	
	void deleteById(int id);

}
