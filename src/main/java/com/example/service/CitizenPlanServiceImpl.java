package com.example.service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.binding.SearchCriteria;
import com.example.email.EmailUtils;
import com.example.entity.CitizenPlan;
import com.example.repo.CitizenPlanRepo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

	@Autowired
	private CitizenPlanRepo citizenRepo;
	
	@Autowired
	private EmailUtils email;

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
	public List<CitizenPlan> searchCitizenInfo(SearchCriteria Criteria) {

		CitizenPlan entity = new CitizenPlan();

		if (StringUtils.isNotBlank(Criteria.getPlanName())) {
			entity.setPlanName(Criteria.getPlanName());
		}

		if (StringUtils.isNotBlank(Criteria.getPlanStatus())) {
			entity.setPlanStatus(Criteria.getPlanStatus());
		}
		if (StringUtils.isNotBlank(Criteria.getGender())) {
			entity.setGender(Criteria.getGender());
		}

		if (null != Criteria.getStartDate()) {
			entity.setPlan_Start_Date(Criteria.getStartDate());
		}

		if (null != Criteria.getEndDate()) {
			entity.setPlan_End_Date(Criteria.getEndDate());
		}

		// QBE
		Example<CitizenPlan> of = Example.of(entity);
		return citizenRepo.findAll(of);
	}

	@Override
	public void generatePdf(HttpServletResponse res) throws DocumentException, IOException {

		
		Document document = new Document(PageSize.A4);
		
		ServletOutputStream outputStream = res.getOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();
		
		Document document1 = new Document(PageSize.A4);
		
		File f =new File("Data.pdf");
		FileOutputStream fos=new FileOutputStream(f);
		PdfWriter.getInstance(document1, fos);
		document1.open();

		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(12);
		font.setColor(Color.BLUE);
		Paragraph p = new Paragraph("Citizen Plans Info", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		
		document.add(p);
		document1.add(p);
		
		PdfPTable table=new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] {3,3,3,3,3,3,3});
		table.setSpacingBefore(5);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.black);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Phno", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Ssn", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planName", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planStatus", font));
		table.addCell(cell);
		
		List<CitizenPlan> all = citizenRepo.findAll();
		
		for(CitizenPlan record :all) {
			table.addCell(record.getName());
			table.addCell(record.getEmail());
			table.addCell(String.valueOf(record.getPhno()));
			table.addCell(record.getGender());
			table.addCell(String.valueOf(record.getSsn()));
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
		}
		
		document.add(table);
		document1.add(table);
		
		document.close();
		outputStream.close();
		
		document1.close();
		fos.close();
		

		email.fileSender(f);
	}

	@Override
	public void generateExal(HttpServletResponse res) throws IOException {

		List<CitizenPlan> all = citizenRepo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Data");
		HSSFRow createRow = sheet.createRow(0);

		createRow.createCell(0).setCellValue("Name");
		createRow.createCell(1).setCellValue("Email");
		createRow.createCell(2).setCellValue("Phno");
		createRow.createCell(3).setCellValue("Gender");
		createRow.createCell(4).setCellValue("Ssn");
		createRow.createCell(5).setCellValue("planName");
		createRow.createCell(6).setCellValue("planStatus");

		int rowNums = 1;
		for (CitizenPlan record : all) {

			HSSFRow row = sheet.createRow(rowNums);

			row.createCell(0).setCellValue(record.getName());
			row.createCell(1).setCellValue(record.getEmail());
			row.createCell(2).setCellValue(record.getPhno());
			row.createCell(3).setCellValue(record.getGender());
			row.createCell(4).setCellValue(record.getSsn());
			row.createCell(5).setCellValue(record.getPlanName());
			row.createCell(6).setCellValue(record.getPlanStatus());

			rowNums++;

		}
		File f =new File("Data.xls");
		FileOutputStream fos=new FileOutputStream(f);
		workbook.write(fos);
		email.fileSender(f);
		
		
		ServletOutputStream outputStream = res.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

}
