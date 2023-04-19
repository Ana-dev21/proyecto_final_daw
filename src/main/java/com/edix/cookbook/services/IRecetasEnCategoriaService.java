package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasEnCategoria;

public interface IRecetasEnCategoriaService {
	
	List<RecetasEnCategoria> findAll();
	
	RecetasEnCategoria findById(int id);
	
	RecetasEnCategoria save(RecetasEnCategoria recetasEnCategoria);
	
	void deleteById(int id);
	
	List<Receta> findAllByCategoriaContaining(String categoria);
}
