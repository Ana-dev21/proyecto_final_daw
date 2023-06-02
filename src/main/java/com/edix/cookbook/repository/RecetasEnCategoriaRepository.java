package com.edix.cookbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasEnCategoria;

public interface RecetasEnCategoriaRepository extends JpaRepository<RecetasEnCategoria,Integer>{

	@Query("SELECT rc.receta FROM RecetasEnCategoria rc where rc.categoria.nombre LIKE %:categoria%")
	List<Receta> findAllByCategoriaContaining(String categoria);

	@Query("SELECT rc.receta FROM RecetasEnCategoria rc where rc.categoria.idCategoria = :idCategoria")
	List<Receta> findAllByCategoriaIdCategoria(int idCategoria);
}
