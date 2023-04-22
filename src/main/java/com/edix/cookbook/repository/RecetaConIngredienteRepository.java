package com.edix.cookbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasConIngrediente;

public interface RecetaConIngredienteRepository extends JpaRepository<RecetasConIngrediente,Integer>{

	
	@Query("SELECT ri.receta FROM RecetasConIngrediente ri where ri.ingrediente.nombre like %:nombre%")
	List<Receta> findAllByIngrediente(String nombre);
	
	@Query("SELECT ri.receta FROM RecetasConIngrediente ri where ri.ingrediente.idIngrediente in :listaIngredientes "
			+ "GROUP BY ri.receta HAVING COUNT(DISTINCT ri.ingrediente.idIngrediente) = :numeroIng")
	List<Receta> findAllByMultipleIngredientes(List<Integer> listaIngredientes, long numeroIng);
	
}