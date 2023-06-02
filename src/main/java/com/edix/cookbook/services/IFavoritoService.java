package com.edix.cookbook.services;

import com.edix.cookbook.models.Receta;

import java.util.List;

public interface IFavoritoService {
	
    List<Receta> findAllFavoritosByIdUsuario(int idUsuario);

    Receta addFavorito(int idUsuario, int idReceta);

    boolean isFavorita(int idUsuario, int idReceta);

    boolean deleteFavorito(int idUsuario, int idReceta);
}
