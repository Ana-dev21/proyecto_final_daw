package com.edix.cookbook.repository;

import com.edix.cookbook.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cookbook.models.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	Usuario findByUsername(String username);

	@Query("SELECT c from Comentario  c where c.usuario.idUsuario = :idUsuario")
	List<Comentario> obtenerComentariosUsuario(int idUsuario);
}
