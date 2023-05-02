package com.edix.cookbook.services;

import java.math.BigDecimal;
import java.util.List;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasConIngrediente;

public interface IRecetasConIngredienteService {
	
	List<RecetasConIngrediente> findAll();
	
	RecetasConIngrediente findById(int id);
	
	RecetasConIngrediente save(RecetasConIngrediente recetasConIngrediente) throws Exception;
	
	void deleteById(int id);
	
	List<Receta> findAllByIngrediente(String nombre);
	
	List<Receta> findAllByMultipleIngredientes(List<Integer> listaIngredientes);

	List<RecetasConIngrediente> findAllIngredientesByReceta(int idReceta);

	boolean nuevoIngredienteEnReceta(int idReceta, int idIngrediente, String unidadMedida, BigDecimal cantidad);
}
