package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cookbook.model.beans.Receta;

public interface RecetaRepository extends JpaRepository<Receta,Integer>{

}
