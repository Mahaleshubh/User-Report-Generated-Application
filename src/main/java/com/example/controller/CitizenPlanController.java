package com.example.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.binding.SearchCriteria;
import com.example.entity.CitizenPlan;
import com.example.service.CitizenPlanService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CitizenPlanController {
	@Autowired
	private CitizenPlanService service;

	@GetMapping("/")
	public String index(Model model) {

		List<String> planNames = service.getPlanNames();
		List<String> planStatus = service.getPlanStatus();

		model.addAttribute("planNames", planNames);
		model.addAttribute("planStatus", planStatus);
		model.addAttribute("search", new SearchCriteria());

		return "index";

	}

	@PostMapping("/filter-data")
	public String handleSearchBtn(@ModelAttribute("search") SearchCriteria criteria, Model model) {

		System.out.println(criteria);

		List<CitizenPlan> searchCitizenInfo = service.searchCitizenInfo(criteria);
		model.addAttribute("citizens", searchCitizenInfo);
		return "index";

	}

	@GetMapping("/exal")
	public void generatingExalReport(HttpServletResponse response) throws IOException {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=data.xls";
		response.addHeader(headerKey, headerValue);
		service.generateExal(response);
	}

	@GetMapping("/pdf")
	public void generatingPdfReport(HttpServletResponse response) throws IOException {

		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=data.pdf";
		response.addHeader(headerKey, headerValue);
		try {
			service.generatePdf(response);
		} catch (DocumentException e) {
			// Handle DocumentException
			e.printStackTrace();
			response.getWriter().println("Error occurred while generating PDF: " + e.getMessage());
		}
	}
}
