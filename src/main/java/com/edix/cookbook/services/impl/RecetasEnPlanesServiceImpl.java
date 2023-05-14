package com.edix.cookbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.repository.RecetasEnPlanesRepository;
import com.edix.cookbook.services.IRecetasEnPlanesService;

@Service
public class RecetasEnPlanesServiceImpl implements IRecetasEnPlanesService{

	@Autowired RecetasEnPlanesRepository reprepo;
	
	@Override
	public List<Receta> findAllByPlan(int idPlan) {
		
		return reprepo.findAllByPlan(idPlan);
	}

}
