package com.edix.cookbook.services;

import java.util.List;
import java.util.Optional;

import com.edix.cookbook.models.Comentario;
import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;

public interface IRecetaService {
	List<Receta> findAll();
	
	Optional<Receta> findById(int idReceta) throws Exception;
	
	Receta save(Receta receta);
	
	void deleteById(int id);
	
	//TODO Repository Query
	List<Receta> findAllOrderedByNombre();
	
	//TODO Repository Query
	List<Receta> findAllByNombreContaining(String nombre);
	
	//TODO Repository Query
	List<Receta> findAllByUsuario(Usuario Usuario);
	
	//TODO Repository Query
	List<Receta> findAllByTiempoCoccionLessThan(int tiempo);

	List<Comentario>listarComentarios(int idReceta);
	
//	//TODO Repository Query
//	List<Receta> findAllByIngredientesIn(List<Ingrediente> ingredientes);
}
