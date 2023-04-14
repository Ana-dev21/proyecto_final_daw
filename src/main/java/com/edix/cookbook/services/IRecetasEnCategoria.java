package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.RecetasEnCategoria;

public interface IRecetasEnCategoria {
	
	List<RecetasEnCategoria> findAll();
	
	RecetasEnCategoria findById(int id);
	
	RecetasEnCategoria save(RecetasEnCategoria recetasEnCategoria);
	
	void deleteById(int id);
}
