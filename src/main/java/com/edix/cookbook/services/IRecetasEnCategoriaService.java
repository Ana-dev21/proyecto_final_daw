package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Receta;

public interface IRecetasEnCategoriaService {
	
	List<Receta> findAllByCategoriaContaining(String categoria);

	List<Receta> findAllByCategoriaIdCategoria(int idCategoria);
}
