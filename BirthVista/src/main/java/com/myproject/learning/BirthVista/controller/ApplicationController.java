package com.myproject.learning.BirthVista.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.learning.BirthVista.dto.AddApplicationDto;
import com.myproject.learning.BirthVista.dto.ApplicationUpdateDto;
import com.myproject.learning.BirthVista.dto.ApplicationUserDto;
import com.myproject.learning.BirthVista.enums.ApplicationStatus;
import com.myproject.learning.BirthVista.model.Application;
import com.myproject.learning.BirthVista.model.User;
import com.myproject.learning.BirthVista.service.ApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Application API")
public class ApplicationController {
	
	ApplicationService applicationService;
	
	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@Operation(summary = "Add new application", description = "Add new application for applicant")
	@PostMapping("/user/addApplication")
	public ResponseEntity<?> addApplication(@RequestBody @Valid AddApplicationDto applicationDto){
		
	    User agent = applicationService.getCurrentUser();
		
		 applicationService.addApplication(applicationDto,agent);
		return ResponseEntity.ok("Application Submitted Successfully!!");
	}
	
	@Operation(summary = "Get application of Logged In user", description = "Get all the Application applied By Current logged in User")
	@GetMapping("/user/getApplicationCurrentUser")
	public ResponseEntity<?> getApplicationUsingEmail(){
		User agent = applicationService.getCurrentUser();
		String agentEmail = agent.getEmail();
		List<Application> applicationList = applicationService.getApplicationUsingEmail(agentEmail);
		if(applicationList != null) {
			return ResponseEntity.ok(applicationList);			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
	
	@Operation(summary = "Get All applications", description = "Fetch all the Applications")
	@GetMapping("/admin/getAllApplication")
	public ResponseEntity<?> getAllApplication(){
		List<Application> applicationList = applicationService.getAllApplication();
		if(applicationList != null) {
			return ResponseEntity.ok(applicationList);			
		}
		return ResponseEntity.ok("No Application Found");
	}
	
	@Operation(summary = "Update Application Remark", description = "Admin route to Verify and Reject the application")
	@PutMapping("/admin/updateApplication/remark")
	public ResponseEntity<?> updateApplication(@RequestBody @Valid ApplicationUpdateDto applicationDto){
		
		
		boolean isUpdated = applicationService.updateApplication(applicationDto);
		if(isUpdated) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Application Remark Updated Successfully");
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
		
	}
	
	@Operation(summary = "Get New/Pending applications", description = "Fetch all the Pending/New Applications")
	@GetMapping("/admin/getNewApplication")
	public ResponseEntity<?> getNewApplication(){
		List<Application> applicationList = applicationService.getApplicationViaStatus(ApplicationStatus.PENDING);
		if(applicationList != null) {
			return ResponseEntity.ok(applicationList);			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
	
	@Operation(summary = "Get Verfied applications", description = "Fetch all the Verified Applications")
	@GetMapping("/admin/getAcceptedApplication")
	public ResponseEntity<?> getAcceptedApplication(){
		List<Application> applicationList = applicationService.getApplicationViaStatus(ApplicationStatus.VERIFIED);
		if(applicationList != null) {
			return ResponseEntity.ok(applicationList);			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
	
	@Operation(summary = "Get rejected applications", description = "Fetch all the rejected Applications")
	@GetMapping("/admin/getRejectedApplication")
	public ResponseEntity<?> getRejectedApplication(){
		List<Application> applicationList = applicationService.getApplicationViaStatus(ApplicationStatus.REJECTED);
		if(applicationList != null) {
			return ResponseEntity.ok(applicationList);			
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
	
	@Operation(summary = "Get application by ID", description = "Fetch a single application using its ID")
	@GetMapping("/user/getAppDetailsById/{id}")
	public ResponseEntity<?> getAppDetailsById(@PathVariable long id ){
		
		Application appDetails = applicationService.getAppDetailsById(id);
		if(appDetails != null) {
			return ResponseEntity.ok(appDetails);			
		} 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	}
	
	@Operation(summary = "Get All Certificate Details of Logged In User", description = "Fetch All application data for certificate")
	@GetMapping("user/getAllCertificate")
	public ResponseEntity<?> getAllCertificate(){
		try {
			User agent = applicationService.getCurrentUser();
			 
	        String agentEmail = agent.getEmail();
	        
	        List<Application> applicationList = applicationService.getAllCertificate(agentEmail, ApplicationStatus.VERIFIED);

	        if (!applicationList.isEmpty()) {
	            return ResponseEntity.ok(applicationList);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No verified applications found for this agent.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An error occurred while fetching certificate data: " + e.getMessage());
	    }
	}
	
	@Operation(summary = "Get Certificate Details by ID", description = "Fetch a single application details with its user data")
	@GetMapping("/user/getCertificateDetails/{Id}")
	public ResponseEntity<?> getCertificateDetails(@PathVariable Long Id ){
		try {
			ApplicationUserDto application = applicationService.getCertificateDetails(Id, ApplicationStatus.VERIFIED);

	        if (application != null) {
	            return ResponseEntity.ok(application);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No verified applications found for this Id.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An error occurred while fetching certificate data: " + e.getMessage());
	    }
	}
	
	@Operation(summary = "Get Certificate in PDF Form by ID", description = "Fetch a single application Birth certificte PDF using application Id")
	@GetMapping("/user/getCertificatePdf/{Id}")
	public ResponseEntity<?> getCertificatePdf(@PathVariable Long Id ){
		try {
			 
			byte[] pdfBytes = applicationService.getCertificatePdf(Id, ApplicationStatus.VERIFIED);
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "certificate_" + Id + ".pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An error occurred while fetching certificate data: " + e.getMessage());
	    }
	}
	

}
