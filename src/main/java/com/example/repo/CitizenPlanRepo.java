package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer>{

	@Query("SELECT DISTINCT(planName) FROM CitizenPlan")
	public List<String> getPlanNames();
	
	@Query("SELECT DISTINCT(planStatus) FROM CitizenPlan")
	public List<String> getPlanStatus();
}
