package com.edix.cookbook.services.impl;

import com.edix.cookbook.models.Plan;
import com.edix.cookbook.repository.PlanRepository;
import com.edix.cookbook.services.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements IPlanService {

    @Autowired
    PlanRepository pRepo;

    @Override
    public List<Plan> findAll() {
        return pRepo.findAll();
    }

    @Override
    public Plan findById(int id) {
        Optional<Plan> planOpional = pRepo.findById(id);
        if (planOpional.isPresent()) {
            return planOpional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró el plan");
        }
    }


    @Override
    public boolean deleteById(int id) {
    //TODO: implementar
        return false;
    }

    @Override
    public List<Plan> findAllByNombreContaining(String nombre) {
        return pRepo.findAllByNombreContaining(nombre);
    }
}
