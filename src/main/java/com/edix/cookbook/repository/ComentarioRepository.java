package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cookbook.models.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer>{

}
