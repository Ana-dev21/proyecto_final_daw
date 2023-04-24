package com.edix.cookbook.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;

public interface IRecetaService {
	List<Receta> findAll();
	
	Receta findById(int id) throws Exception;
	
	Receta save(Receta receta) throws Exception;
	
	Receta save(Receta receta, MultipartFile imagen) throws Exception;
	
	Receta update(Receta receta) throws Exception;
	
	Receta update(Receta receta, MultipartFile imagen) throws Exception;
	
	void deleteById(int id);
	
	//TODO Repository Query
	List<Receta> findAllOrderedByNombre();
	
	//TODO Repository Query
	List<Receta> findAllByNombreContaining(String nombre);
	
	//TODO Repository Query
	List<Receta> findAllByUsuario(Usuario Usuario);
	
	//TODO Repository Query
	List<Receta> findAllByTiempoCoccionLessThan(int tiempo);

	
	
//	//TODO Repository Query
//	List<Receta> findAllByIngredientesIn(List<Ingrediente> ingredientes);
}
