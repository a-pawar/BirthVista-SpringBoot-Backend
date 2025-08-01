package com.myproject.learning.BirthVista.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myproject.learning.BirthVista.enums.ApplicationStatus;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;
    
    @Column(name = "DOB")
    private LocalDate dateOfBirth;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    
    @Column(name = "father_name")
    private String fatherName;
    
    @Column(name = "permanent_address")
    private String permanentAddress;
    
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "applicant_email", nullable = false,unique = true)
    private String applicantEmail;

    @Column(name = "apply_date")
    private LocalDate applyDate;
    
    @Column(name = "remark")
    private String remark;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;  //PENDING,ACCEPTED,REJECTED

    @Column(name = "updation_date")
    private LocalDate updationDate;
    
    @ManyToOne
    @JoinColumn(name="agent_email",referencedColumnName = "email")
    @JsonIgnore
    private User agent;

    public Application() {}
    
	public Application(Long applicationId, LocalDate dateOfBirth, String gender, String fullName, String placeOfBirth,
			String fatherName, String permanentAddress, String mobileNumber, String applicantEmail, LocalDate applyDate,
			String remark, ApplicationStatus status, LocalDate updationDate, User agent) {
		super();
		this.applicationId = applicationId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.fullName = fullName;
		this.placeOfBirth = placeOfBirth;
		this.fatherName = fatherName;
		this.permanentAddress = permanentAddress;
		this.mobileNumber = mobileNumber;
		this.applicantEmail = applicantEmail;
		this.applyDate = applyDate;
		this.remark = remark;
		this.status = status;
		this.updationDate = updationDate;
		this.agent = agent;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public LocalDate getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(LocalDate updationDate) {
		this.updationDate = updationDate;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", fullName=" + fullName + ", placeOfBirth=" + placeOfBirth + ", fatherName=" + fatherName
				+ ", permanentAddress=" + permanentAddress + ", mobileNumber=" + mobileNumber + ", applicantEmail="
				+ applicantEmail + ", applyDate=" + applyDate + ", remark=" + remark + ", status=" + status
				+ ", updationDate=" + updationDate + ", agent=" + agent + "]";
	}

    
}

