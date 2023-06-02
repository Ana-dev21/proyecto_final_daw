package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Categoria;

public interface ICategoriaService {
	
	List<Categoria> findAll();
	
}
