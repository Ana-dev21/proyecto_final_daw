package com.edix.cookbook.services;

import java.math.BigDecimal;
import java.util.List;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasConIngrediente;

public interface IRecetasConIngredienteService {
	
	RecetasConIngrediente save(RecetasConIngrediente recetasConIngrediente) throws Exception;
	
	List<Receta> findAllByIngrediente(String nombre);
	
	List<Receta> findAllByMultipleIngredientes(List<Integer> listaIngredientes);

	List<RecetasConIngrediente> findAllIngredientesByReceta(int idReceta);

	boolean nuevoIngredienteEnReceta(int idReceta, int idIngrediente, String unidadMedida, BigDecimal cantidad);
}
