package com.edix.cookbook.services.impl;

import java.math.BigDecimal;
import java.util.List;

import com.edix.cookbook.models.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasConIngrediente;
import com.edix.cookbook.repository.RecetaConIngredienteRepository;
import com.edix.cookbook.services.IRecetasConIngredienteService;

@Service
public class RecetasConIngredienteServiceImpl implements IRecetasConIngredienteService{
	
	@Autowired RecetaConIngredienteRepository rciRepo;

	@Autowired RecetaServiceImpl rServ;

	@Autowired IngredienteServiceImpl iServ;

	@Override
	public RecetasConIngrediente save(RecetasConIngrediente recetasConIngrediente) throws Exception {

			if (recetasConIngrediente.getIdRecetaIncrediente() == 0) {
				return rciRepo.save(recetasConIngrediente);
			}else {
				throw new Exception("La Línea con id = " + recetasConIngrediente.getIdRecetaIncrediente() + " ya existe" );
			}

		}

	@Override
	public List<Receta> findAllByIngrediente(String nombre) {
		return rciRepo.findAllByIngrediente(nombre);
	}

	@Override
	public List<Receta> findAllByMultipleIngredientes(List<Integer> listaIngredientes) {
		return rciRepo.findAllByMultipleIngredientes(listaIngredientes, listaIngredientes.size());
	}

	@Override
	public List<RecetasConIngrediente> findAllIngredientesByReceta(int idReceta) {
		return rciRepo.findAllIngredientesByReceta(idReceta);
	}

	@Override
	public boolean nuevoIngredienteEnReceta(int idReceta, int idIngrediente, String unidadMedida, BigDecimal cantidad) {
		try {
			Receta receta = rServ.findById(idReceta);
			Ingrediente ingrediente = iServ.findById(idIngrediente);
			RecetasConIngrediente rci = new RecetasConIngrediente();
			rci.setReceta(receta);
			rci.setIngrediente(ingrediente);
			rci.setUnidadMedida(unidadMedida);
			rci.setCantidad(cantidad);
			System.out.println("Receta Con Ingrediente: " + rci);
			rciRepo.save(rci);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
