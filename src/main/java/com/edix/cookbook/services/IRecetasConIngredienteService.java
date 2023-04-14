package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.RecetasConIngrediente;

public interface IRecetasConIngredienteService {
	
	List<RecetasConIngrediente> findAll();
	
	RecetasConIngrediente findById(int id);
	
	RecetasConIngrediente save(RecetasConIngrediente recetasConIngrediente);
	
	void deleteById(int id);
}
