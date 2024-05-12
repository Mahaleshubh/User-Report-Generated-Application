package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CitizenPlan {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String name;
	private String email;
	private Integer phno;
	private String gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	private LocalDate plan_Start_Date;
	private LocalDate plan_End_Date;
	public CitizenPlan() {
	
	}
	public CitizenPlan(String name, String email, Integer phno, String gender, long ssn, String planName,
			String planStatus, LocalDate plan_Start_Date, LocalDate plan_End_Date) {
		
		this.name = name;
		this.email = email;
		this.phno = phno;
		this.gender = gender;
		this.ssn = ssn;
		this.planName = planName;
		this.planStatus = planStatus;
		this.plan_Start_Date = plan_Start_Date;
		this.plan_End_Date = plan_End_Date;
	}
	public Integer getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(Integer citizenId) {
		this.citizenId = citizenId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPhno() {
		return phno;
	}
	public void setPhno(Integer phno) {
		this.phno = phno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public LocalDate getPlan_Start_Date() {
		return plan_Start_Date;
	}
	public void setPlan_Start_Date(LocalDate plan_Start_Date) {
		this.plan_Start_Date = plan_Start_Date;
	}
	public LocalDate getPlan_End_Date() {
		return plan_End_Date;
	}
	public void setPlan_End_Date(LocalDate plan_End_Date) {
		this.plan_End_Date = plan_End_Date;
	}
	@Override
	public String toString() {
		return "CitizenPlan [citizenId=" + citizenId + ", name=" + name + ", email=" + email + ", phno=" + phno
				+ ", gender=" + gender + ", ssn=" + ssn + ", planName=" + planName + ", planStatus=" + planStatus
				+ ", plan_Start_Date=" + plan_Start_Date + ", plan_End_Date=" + plan_End_Date + "]";
	}
	
	

}
