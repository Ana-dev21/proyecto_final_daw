package com.edix.cookbook.repository;
import com.edix.cookbook.models.Favorito;
import com.edix.cookbook.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    //Método que devuelve una lista de recetas favoritas de un usuario
    @Query("SELECT f.receta FROM Favorito f WHERE f.usuario.idUsuario =?1")
    List<Receta> findRecetasFavoritas(int idUsuario);

    //Método que devuelve un favorito por su usuario y su receta
    @Query("SELECT f FROM Favorito f WHERE f.usuario.idUsuario =?1 AND f.receta.idReceta =?2")
    Favorito findByUsuarioAndReceta(int idUsuario, int idReceta);
}
