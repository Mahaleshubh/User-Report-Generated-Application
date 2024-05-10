package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.binding.SearchCriteria;
import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepo;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {
	
	@Autowired
	private CitizenPlanRepo citizenRepo;

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = citizenRepo.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> planStatus = citizenRepo.getPlanStatus();
		return planStatus;
	}

	@Override
	public List<CitizenPlan> searchCitizenInfo(SearchCriteria Criteria ) {
		
		CitizenPlan entity=new CitizenPlan();
		
		if(StringUtils.isNotBlank(Criteria.getPlanName())) {
			entity.setPlanName(Criteria.getPlanName());
		}
		
		if(StringUtils.isNotBlank(Criteria.getPlanStatus())) {
			entity.setPlanStatus(Criteria.getPlanStatus());
		}
		if(StringUtils.isNotBlank(Criteria.getGender())) {
			entity.setGender(Criteria.getGender());
		}
		
		//QBE
		Example<CitizenPlan> of = Example.of(entity);
		return citizenRepo.findAll(of);
	}

	@Override
	public void generatePdf(HttpServletResponse res) {
	
		
	}

	@Override
	public void generateExal(HttpServletResponse res) {
		// TODO Auto-generated method stub
		
	}

}
