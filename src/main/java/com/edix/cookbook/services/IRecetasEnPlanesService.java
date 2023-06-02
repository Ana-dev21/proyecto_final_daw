package com.edix.cookbook.services;

import java.util.List;

import com.edix.cookbook.models.Receta;

public interface IRecetasEnPlanesService {

	List<Receta> findAllByPlan(int idPlan);
}
