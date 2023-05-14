package com.edix.cookbook.services.impl;

import java.util.List;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.ComentarioRepository;
import com.edix.cookbook.repository.RecetaRepository;
import com.edix.cookbook.repository.UsuarioRepository;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comentario findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comentario save(Comentario comentario) {

		return coRepository.save(comentario);
	}



	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
