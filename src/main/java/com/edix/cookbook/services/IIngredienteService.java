package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Ingrediente;
import com.edix.cookbook.models.Receta;

public interface IIngredienteService {
	
	List<Ingrediente> findAll();
	
	Receta findById(int id);
	
	Receta save(Ingrediente ingrediente);
	
	void deleteById(int id);
}
