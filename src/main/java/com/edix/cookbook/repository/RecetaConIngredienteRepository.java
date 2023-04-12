package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cookbook.model.beans.RecetasConIngrediente;

public interface RecetaConIngredienteRepository extends JpaRepository<RecetasConIngrediente,Integer>{

}
