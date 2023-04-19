package com.edix.cookbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasConIngrediente;
import com.edix.cookbook.repository.RecetaConIngredienteRepository;
import com.edix.cookbook.services.IRecetasConIngredienteService;

@Service
public class RecetasConIngredienteServiceImpl implements IRecetasConIngredienteService{
	
	@Autowired RecetaConIngredienteRepository rciRepo;

	@Override
	public List<RecetasConIngrediente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecetasConIngrediente findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecetasConIngrediente save(RecetasConIngrediente recetasConIngrediente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Receta> findAllByIngrediente(String nombre) {
		
		return rciRepo.findAllByIngrediente(nombre);
	}

}
