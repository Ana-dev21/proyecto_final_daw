package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cookbook.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	Usuario findByUsername(String username);
	
}
