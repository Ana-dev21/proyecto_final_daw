package com.edix.cookbook.services.impl;

import com.edix.cookbook.models.Favorito;
import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.FavoritoRepository;
import com.edix.cookbook.services.IFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoServiceImpl implements IFavoritoService {

    @Autowired
    FavoritoRepository fRepo;

    @Autowired
    RecetaServiceImpl rServ;

    @Autowired
    UsuarioServiceImpl uServ;

    @Override
    public List<Receta> findAllFavoritosByIdUsuario(int idUsuario) {
        return fRepo.findRecetasFavoritas(idUsuario);
    }

    @Override
    public Receta addFavorito(int idUsuario, int idReceta) {

        Receta receta = rServ.buscarPorId(idReceta);
        Usuario usuario = uServ.findById(idUsuario);

        if(usuario == null || receta == null){
            throw new NotFoundException("Usuario o receta no encontrados");
        }

        if (fRepo.findByUsuarioAndReceta(usuario.getIdUsuario(), receta.getIdReceta()) != null){
            throw new DuplicateKeyException("La receta ya está en la lista de favoritos del usuario");
        }

        Favorito favorito = new Favorito();
        favorito.setReceta(receta);
        favorito.setUsuario(usuario);
        fRepo.save(favorito);
        return receta;
    }

    @Override
    public boolean isFavorita(int idUsuario, int idReceta) {
        Receta receta = rServ.buscarPorId(idReceta);
        Usuario usuario = uServ.findById(idUsuario);

        if(usuario == null || receta == null){
            throw new NotFoundException("Usuario o receta no encontrados");
        }

        Favorito favorito = fRepo.findByUsuarioAndReceta(usuario.getIdUsuario(), receta.getIdReceta());

        if (favorito == null){
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteFavorito(int idUsuario, int idReceta) {
        Receta receta = rServ.buscarPorId(idReceta);
        Usuario usuario = uServ.findById(idUsuario);

        if(usuario == null || receta == null){
            throw new NotFoundException("Usuario o receta no encontrados");
        }

        Favorito favorito = fRepo.findByUsuarioAndReceta(usuario.getIdUsuario(), receta.getIdReceta());

        if (favorito == null){
            throw new NotFoundException("La receta no está en la lista de favoritos del usuario");
        }

        fRepo.delete(favorito);
        return true;
    }
}
