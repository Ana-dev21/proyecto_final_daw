package com.edix.cookbook.repository;

import com.edix.cookbook.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlanRepository extends JpaRepository<Plan,Integer> {

    List<Plan> findAllByNombreContaining(String nombre);
}
