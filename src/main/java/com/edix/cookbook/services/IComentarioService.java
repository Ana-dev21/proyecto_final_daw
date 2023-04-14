package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Comentario;

public interface IComentarioService {
	
	List<Comentario> findAll();
	
	Comentario findById(int id);
	
	Comentario save(Comentario comentario);
	
	void deleteById(int id);

}
