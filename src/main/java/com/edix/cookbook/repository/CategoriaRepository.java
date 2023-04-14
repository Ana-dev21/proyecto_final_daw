package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cookbook.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

}
