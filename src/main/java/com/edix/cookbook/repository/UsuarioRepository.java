package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cookbook.model.beans.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

}
