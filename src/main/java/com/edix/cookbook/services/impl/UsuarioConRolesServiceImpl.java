package com.edix.cookbook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.edix.cookbook.models.UsuarioConRoles;
import com.edix.cookbook.repository.UsuariosConRolesRepository;
import com.edix.cookbook.services.IUsuariosConRolesService;

public class UsuarioConRolesServiceImpl implements IUsuariosConRolesService{
	
	@Autowired UsuariosConRolesRepository ucRolRepo;

	@Override
	public UsuarioConRoles getUsuarioConRoles(int idUsuario) {
		
		return ucRolRepo.getUsuarioConRoles(idUsuario);
	}

}
