package com.example.dataLoading;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
		CitizenPlan p1=new CitizenPlan("shubham", "shubham@gmail.com", 125456, "male", 4567, "madical", "Approved", LocalDate.now(), LocalDate.now());
		CitizenPlan p2=new CitizenPlan("rajesh", "rajesh@gmail.com", 5555, "male", 78, "madical", "Denied", LocalDate.now(), LocalDate.now());
		CitizenPlan p3=new CitizenPlan("rani", "rani@gmail.com", 56, "fe-male", 67, "food", "Approved", LocalDate.now(), LocalDate.now());
		CitizenPlan p4=new CitizenPlan("Ganesh", "ganesh2gmail.com", 88552, "male", 784566, "food", "Terminated", LocalDate.now(), LocalDate.now());
		
		List<CitizenPlan> asList = Arrays.asList(p1,p2,p3,p4);
		repo.saveAll(asList);
	}
	
	
}
