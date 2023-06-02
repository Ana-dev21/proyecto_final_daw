package com.edix.cookbook.repository;
import com.edix.cookbook.models.Favorito;
import com.edix.cookbook.models.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    @Query("SELECT f.receta FROM Favorito f WHERE f.usuario.idUsuario =?1")
    List<Receta> findRecetasFavoritas(int idUsuario);

    @Query("SELECT f FROM Favorito f WHERE f.usuario.idUsuario =?1 AND f.receta.idReceta =?2")
    Favorito findByUsuarioAndReceta(int idUsuario, int idReceta);
}
