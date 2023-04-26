package com.edix.cookbook.services.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.edix.cookbook.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.Usuario;
import com.edix.cookbook.repository.RecetaRepository;
import com.edix.cookbook.services.IRecetaService;

@Service
public class RecetaServiceImpl implements IRecetaService{

	@Autowired RecetaRepository reRepo;
	
	@Override
	public List<Receta> findAll() {
		return reRepo.findAll();
	}

	@Override
	public Receta findById(int id) throws Exception {
		Optional<Receta> recetaOptional = reRepo.findById(id);
        if (recetaOptional.isPresent()) {
            return recetaOptional.get();
        } else {
            throw new Exception("No se encontró la receta con el Id " + id);
        }
	}

	@Override
	public Receta create(Receta receta) throws Exception {
		
		if (reRepo.findById(receta.getIdReceta()) != null) {
			return reRepo.save(receta);
		}else {
			throw new Exception("La Receta con ese id ya existe" );
		}
		
	}
	
	@Override
	public Receta create(Receta receta, MultipartFile imagen) throws Exception {

		if (reRepo.findById(receta.getIdReceta()) == null && !imagen.isEmpty()) {
			Receta recetaCreada = reRepo.save(receta);
			return this.saveImage(recetaCreada.getIdReceta(), imagen);
		} else {
			throw new Exception("Ha ocurrido un error al crear la receta");
		}
	}
	
	@Override
	public Receta update(Receta receta) throws Exception {
		if (reRepo.findById(receta.getIdReceta()) != null) {
			return reRepo.save(receta);
		}else {
			throw new Exception("Ha ocurrido un error al actualizar la receta" );
		}
	}

	@Override
	public Receta update(Receta receta, MultipartFile imagen) throws Exception {
		//Guardar imagen
		if (reRepo.findById(receta.getIdReceta()) != null) {
			return reRepo.save(receta);
		}else {
			throw new Exception("Ha ocurrido un error al guardar la receta" );
		}
	}
	
	@Override
	public Receta saveImage(int idReceta, MultipartFile imagen) throws Exception {
		
		Path directorioImagenes = Paths.get("src//main//resources//static/uploads");
		String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
		Receta receta = this.findById(idReceta);

		// recuperar bytes de la imagen recibida
		byte[] bytesImg = imagen.getBytes();
		
		// Guardar imagen en ruta
		Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + idReceta + "-" +imagen.getOriginalFilename());
		Files.write(rutaCompleta, bytesImg);

		// Registrar ruta en atributo de imagen
		receta.setImagen(imagen.getOriginalFilename());

		Receta recetaGuardada = this.update(receta);
		
		return recetaGuardada;
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Receta> findAllOrderedByNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receta> findAllByNombreContaining(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receta> findAllByUsuario(Usuario Usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receta> findAllByTiempoCoccionLessThan(int tiempo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> listarComentarios(int idReceta) {
		return reRepo.comentariosEnReceta(idReceta);
	}

//	@Override
//	public List<Receta> findAllByIngredientesIn(List<Ingrediente> ingredientes) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
