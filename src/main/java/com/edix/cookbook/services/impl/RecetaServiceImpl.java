package com.edix.cookbook.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.RecetaRepository;
import com.edix.cookbook.services.IRecetaService;

@Service
public class RecetaServiceImpl implements IRecetaService{

	@Autowired RecetaRepository reRepo;
	
	@Override
	public List<Receta> findAll() {
		return reRepo.findAll();
	}

	@Override
	public Receta findById(int id) throws Exception {
		Optional<Receta> recetaOptional = reRepo.findById(id);
        if (recetaOptional.isPresent()) {
            return recetaOptional.get();
        } else {
            throw new Exception(" No se encontró la receta con el Id " + id);
        }
	}

	@Override
	public Receta save(Receta receta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Receta> findAllOrderedByNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receta> findAllByNombreContaining(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receta> findAllByUsuario(Usuario Usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receta> findAllByTiempoCoccionLessThan(int tiempo) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Receta> findAllByIngredientesIn(List<Ingrediente> ingredientes) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
