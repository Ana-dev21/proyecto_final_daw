package com.edix.cookbook.services.impl;

import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.models.UsuarioConPlan;
import com.edix.cookbook.repository.UsuarioConPLanRepository;
import com.edix.cookbook.services.IUsuarioConPLanService;
import com.edix.cookbook.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioConPLanServiceImpl implements IUsuarioConPLanService {

    @Autowired
    UsuarioConPLanRepository uRepo;
    @Autowired
    UsuarioServiceImpl uService;
    
    @Override
    public UsuarioConPlan findByIdUsuario(int idUsuario) {
        Usuario aux = uService.findById(idUsuario);
        try {
            return uRepo.findByUsuario_IdUsuario(aux.getIdUsuario());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UsuarioConPlan save(UsuarioConPlan usuarioConPlan) {
        return uRepo.save(usuarioConPlan);
    }
}
