package com.edix.cookbook.services;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.models.UsuarioConPlan;

public interface IUsuarioConPLanService {

    UsuarioConPlan findByIdUsuario(int idUsuario);

}
