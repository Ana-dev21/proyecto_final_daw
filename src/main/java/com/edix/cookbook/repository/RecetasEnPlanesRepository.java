package com.edix.cookbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cookbook.models.Receta;
import com.edix.cookbook.models.RecetasEnPlanes;

public interface RecetasEnPlanesRepository extends JpaRepository<RecetasEnPlanes,Integer>{

	@Query("SELECT rp.receta FROM RecetasEnPlanes rp where rp.plan.id = :idPlan")
	List<Receta> findAllByPlan(int idPlan);
}
