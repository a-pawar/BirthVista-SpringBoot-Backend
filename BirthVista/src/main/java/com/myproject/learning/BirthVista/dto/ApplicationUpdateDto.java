package com.myproject.learning.BirthVista.dto;

import com.myproject.learning.BirthVista.enums.ApplicationStatus;

import jakarta.validation.constraints.NotNull;


public class ApplicationUpdateDto {

	@NotNull(message = "ApplicationId is required")
	private Long ApplicationId;
	
	private String remark;
    
    private ApplicationStatus status;

	public ApplicationUpdateDto() {
		super();
	}

	public ApplicationUpdateDto(@NotNull(message = "ApplicationId is required") Long applicationId, String remark,
			ApplicationStatus status) {
		super();
		ApplicationId = applicationId;
		this.remark = remark;
		this.status = status;
	}

	public Long getApplicationId() {
		return ApplicationId;
	}

	public void setApplicationId(Long applicationId) {
		ApplicationId = applicationId;
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
    
    
}
