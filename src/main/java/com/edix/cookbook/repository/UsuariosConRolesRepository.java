package com.edix.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cookbook.models.UsuarioConRoles;

public interface UsuariosConRolesRepository extends JpaRepository<UsuarioConRoles,Integer>{
	
	@Query("SELECT ur FROM UsuarioConRoles ur where ur.usuario.idUsuario = :idUsuario")
	UsuarioConRoles getUsuarioConRoles(int idUsuario);

}
