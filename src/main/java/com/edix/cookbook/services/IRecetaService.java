package com.edix.cookbook.services;

import java.util.List;
import com.edix.cookbook.models.Comentario;
import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;

import org.springframework.web.multipart.MultipartFile;

public interface IRecetaService {
	
	List<Receta> findAll();
	
	Receta findById(int idReceta) throws Exception;

    Receta buscarPorId(int id);

    Receta create(Receta receta) throws Exception;
	
	Receta create(Receta receta, MultipartFile imagen) throws Exception;
	
	Receta update(Receta receta) throws Exception;
	
	Receta update(Receta receta, MultipartFile imagen) throws Exception;
	
	void deleteById(int id);
	
	List<Comentario>listarComentarios(int idReceta);

	Receta saveImage(int idReceta, MultipartFile imagen) throws Exception;

	List<Receta> findAllByUsuario_IdUsuario(int idUsuario);
}
