package com.edix.cookbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Categoria;
import com.edix.cookbook.repository.CategoriaRepository;
import com.edix.cookbook.services.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	@Autowired CategoriaRepository caRepo;

	@Override
	public List<Categoria> findAll() {
		
		return caRepo.findAll();
	}

	@Override
	public Categoria findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria save(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
