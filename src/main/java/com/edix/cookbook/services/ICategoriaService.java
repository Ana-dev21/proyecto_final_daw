package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Categoria;

public interface ICategoriaService {
	List<Categoria> findAll();
	
	Categoria findById(int id);
	
	Categoria save(Categoria categoria);
	
	void deleteById(int id);
}
