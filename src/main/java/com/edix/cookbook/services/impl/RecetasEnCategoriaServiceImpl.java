package com.edix.cookbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasEnCategoria;
import com.edix.cookbook.repository.RecetasEnCategoriaRepository;
import com.edix.cookbook.services.IRecetasEnCategoriaService;

@Service
public class RecetasEnCategoriaServiceImpl implements IRecetasEnCategoriaService{
	
	@Autowired RecetasEnCategoriaRepository reCaRepo;

	@Override
	public List<Receta> findAllByCategoriaContaining(String categoria) {
		
		return reCaRepo.findAllByCategoriaContaining(categoria);
	}

	@Override
	public List<Receta> findAllByCategoriaIdCategoria(int idCategoria) {
		return reCaRepo.findAllByCategoriaIdCategoria(idCategoria);
	}

}
