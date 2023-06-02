package com.edix.cookbook.services;

import com.edix.cookbook.models.Plan;

import java.util.List;

public interface IPlanService {

    List<Plan> findAll();
    
    Plan findById(int id);
    
    List<Plan> findAllByNombreContaining(String nombre);
}
