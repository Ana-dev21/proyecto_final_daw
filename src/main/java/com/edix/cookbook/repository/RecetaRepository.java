package com.edix.cookbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;

public interface RecetaRepository extends JpaRepository<Receta,Integer>{

	@Query("SELECT r FROM Receta r ORDER BY r.nombre")
	List<Receta> findAllOrderedByNombre();
		
	@Query("SELECT r FROM Receta r WHERE r.nombre LIKE %:nombre%")
	List<Receta> findAllByNombreContaining(String nombre);
	
	@Query("SELECT r FROM Receta r WHERE r.usuario = :usuario")
	List<Receta> findAllByUsuario(Usuario usuario);
		
	@Query("SELECT r FROM Receta r WHERE r.tiempoCoccion < :tiempo")
	List<Receta> findAllByTiempoCoccionLessThan(int tiempo);
	
}

