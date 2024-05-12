package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.binding.SearchCriteria;
import com.example.entity.CitizenPlan;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface CitizenPlanService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> searchCitizenInfo(SearchCriteria sc);
	
	public void generatePdf(HttpServletResponse res) throws DocumentException, IOException;
	
	public void generateExal(HttpServletResponse res)throws DocumentException, IOException;
	
}
