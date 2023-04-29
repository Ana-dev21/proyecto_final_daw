package com.edix.cookbook.services.impl;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.repository.FavoritoRepository;
import com.edix.cookbook.services.IFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoServiceImpl implements IFavoritoService {

    @Autowired
    FavoritoRepository fRepo;

    @Override
    public List<Receta> findAllFavoritosById(int idUsuario) {
        return fRepo.findRecetasFavoritas(idUsuario);
    }
}
