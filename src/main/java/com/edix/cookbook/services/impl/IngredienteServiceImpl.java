package com.edix.cookbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Ingrediente;
import com.edix.cookbook.models.Receta;
import com.edix.cookbook.repository.IngredienteRepository;
import com.edix.cookbook.services.IIngredienteService;

@Service
public class IngredienteServiceImpl implements IIngredienteService{
	
	@Autowired IngredienteRepository inRepo;

	@Override
	public List<Ingrediente> findAll() {
		return inRepo.findAll();
	}

	@Override
	public Receta findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receta save(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
