package com.edix.cookbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Ingrediente;
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
	public Ingrediente findById(int id) {
		return inRepo.findById(id).orElse(null);
	}

}
