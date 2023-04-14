package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cookbook.models.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer>{

}
