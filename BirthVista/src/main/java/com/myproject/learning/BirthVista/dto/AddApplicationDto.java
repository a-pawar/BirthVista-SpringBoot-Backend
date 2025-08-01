package com.myproject.learning.BirthVista.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AddApplicationDto {
	
		@Email(message = "Please provide a valid email")
	    private String applicantEmail;
	
		
	    private LocalDate dateOfBirth;
	    
	 	@NotBlank(message = "Gender is required")
	    private String gender;
	    
	    @NotBlank(message = "Name is required")
	    private String fullName;
	    
	    @NotBlank(message = "Place Of Birth is required")
	    private String placeOfBirth;
	    
	    @NotBlank(message = "Father Name is required")
	    private String fatherName;
	    
	    private String permanentAddress;
	    
	    private String mobileNumber;

	    

		public AddApplicationDto() {
		}

		public AddApplicationDto( @Email(message = "Please provide a valid email") String applicantEmail,
				@NotBlank(message = "Date Of Birth is required") LocalDate dateOfBirth,
				@NotBlank(message = "Gender is required") String gender,
				@NotBlank(message = "Name is required") String fullName,
				@NotBlank(message = "Place Of Birth is required") String placeOfBirth,
				@NotBlank(message = "Father Name is required") String fatherName, String permanentAddress,
				String mobileNumber) {
			super();
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.fullName = fullName;
			this.placeOfBirth = placeOfBirth;
			this.fatherName = fatherName;
			this.permanentAddress = permanentAddress;
			this.mobileNumber = mobileNumber;
			this.applicantEmail = applicantEmail;
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
		
		
	    
	    

}
