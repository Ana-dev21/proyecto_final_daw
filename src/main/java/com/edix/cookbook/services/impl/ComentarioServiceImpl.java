package com.edix.cookbook.services.impl;

import java.util.List;

import com.edix.cookbook.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Comentario;
import com.edix.cookbook.services.IComentarioService;

@Service
public class ComentarioServiceImpl implements IComentarioService{
	
	@Autowired
	ComentarioRepository coRepository;

	@Override
	public List<Comentario> findAll() {
		return coRepository.findAll();
	}
	
	@Override
	public Comentario findById(int id) {
		return coRepository.findById(id).orElse(null);
	}

	@Override
	public Comentario save(Comentario comentario) {
		return coRepository.save(comentario);
	}

	@Override
	public void deleteById(int id) {
		coRepository.deleteById(id);
	}



}
