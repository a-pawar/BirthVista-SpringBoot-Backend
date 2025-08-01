package com.myproject.learning.BirthVista.dto;


public interface ApplicationUserDto {
	//application field
	 Long getApplicationId();
	 String getApplicantName();
	 String getApplicantFatherName();
	 String getStatus();
	 String getRemark();
	 
	 //user field
	 String getAgentFirstName();
	 String getAgentLastName();
	 String getAgentMobileNumber();
    
}
