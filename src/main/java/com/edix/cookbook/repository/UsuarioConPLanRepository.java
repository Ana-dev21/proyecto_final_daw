package com.edix.cookbook.repository;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.models.UsuarioConPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioConPLanRepository  extends JpaRepository<UsuarioConPlan,Integer> {

        UsuarioConPlan findByUsuario_IdUsuario(int idUsuario);
}
