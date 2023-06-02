package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Ingrediente;

public interface IIngredienteService {
	
	List<Ingrediente> findAll();
	
	Ingrediente findById(int id);
	
}
