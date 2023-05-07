package com.edix.cookbook.services;

import com.edix.cookbook.models.Receta;

import java.util.List;

public interface IFavoritoService {

    List<Receta> findAllFavoritosById(int idUsuario);
}
