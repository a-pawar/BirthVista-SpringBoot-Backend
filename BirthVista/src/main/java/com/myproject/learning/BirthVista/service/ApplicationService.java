package com.myproject.learning.BirthVista.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myproject.learning.BirthVista.config.UserDetailsImpl;
import com.myproject.learning.BirthVista.dto.AddApplicationDto;
import com.myproject.learning.BirthVista.dto.ApplicationUpdateDto;
import com.myproject.learning.BirthVista.dto.ApplicationUserDto;
import com.myproject.learning.BirthVista.dto.Notification;
import com.myproject.learning.BirthVista.enums.ApplicationStatus;
import com.myproject.learning.BirthVista.exception.ResourceNotFoundException;
import com.myproject.learning.BirthVista.model.Application;
import com.myproject.learning.BirthVista.model.User;
import com.myproject.learning.BirthVista.pdf.CertificatePdfGenerator;
import com.myproject.learning.BirthVista.repository.ApplicationRepository;


@Service
public class ApplicationService {
	
	ApplicationRepository applicationRepository;
	
	@Autowired
	private SimpMessagingTemplate msgTemplate;
	
	@Autowired
	private CertificatePdfGenerator pdfGenerator;

	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	public User getCurrentUser() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    return userDetails.getUser(); 
	}
	
	public Application addApplication(AddApplicationDto applicationDto,User agent) {
		
		Application application = new Application();
		
		application.setApplicantEmail(applicationDto.getApplicantEmail());
		application.setDateOfBirth(applicationDto.getDateOfBirth());
		application.setFatherName(applicationDto.getFatherName());
		application.setFullName(applicationDto.getFullName());
		application.setGender(applicationDto.getGender());
		application.setMobileNumber(applicationDto.getMobileNumber());
		application.setPermanentAddress(applicationDto.getPermanentAddress());
		application.setPlaceOfBirth(applicationDto.getPlaceOfBirth());
		
		application.setStatus(ApplicationStatus.PENDING);
		application.setApplyDate(LocalDate.now());
		application.setAgent(agent);
		
		Application savedApplication= applicationRepository.save(application);
		
		return savedApplication;
	}
	
	public List<Application> getApplicationUsingEmail(String agentEmail) {
		
		Optional<List<Application>> optList = applicationRepository.findByAgentEmail(agentEmail);
		
		if(optList.isPresent()) {
			List<Application> appList = optList.get();
			return appList;
		}
		return null;
	}
	
	public List<Application> getAllApplication() {
		
		List<Application> appList = applicationRepository.findAll();
		
			return appList;
		
	}
	
	public Boolean updateApplication(ApplicationUpdateDto applicationDto) {
		Optional<Application> existingAppOpt = applicationRepository.findById(applicationDto.getApplicationId());
		
		if(existingAppOpt.isPresent()) {
			
			Application existingApp = existingAppOpt.get();
			
			if(applicationDto.getStatus()!=null) {
				existingApp.setUpdationDate(LocalDate.now());
				existingApp.setStatus(applicationDto.getStatus());
				if(applicationDto.getRemark()!=null)existingApp.setRemark(applicationDto.getRemark());
			}
			Application updatedApplication= applicationRepository.save(existingApp);
			
			Notification notify = new Notification();
			
			notify.setContent("Applicant Email: "+updatedApplication.getApplicantEmail()+". Application is "+updatedApplication.getStatus()+" by the admin."+" Remark: "
			+ updatedApplication.getRemark());
			
			
			msgTemplate.convertAndSend("/topic/"+updatedApplication.getAgent().getEmail(),notify);
//			msgTemplate.convertAndSend("/topic/om@gmail.com",notify);

			return true;
		}else {
			throw new ResourceNotFoundException("Application not found");
			
		}
		
	}


	public List<Application> getApplicationViaStatus(ApplicationStatus status) {
		Optional<List<Application>> appListOpt = applicationRepository.findByStatus(status);
		
		if(appListOpt.isPresent()) {
			List<Application> appList = appListOpt.get();
			return appList;
		}
		
		return null;
	}


	public Application getAppDetailsById(long id) {
		Optional<Application> appOpt = applicationRepository.getAppDetailsById(id);
		
		if(appOpt.isPresent()) {
			Application applicationDetails = appOpt.get();
			
			return applicationDetails;
		}
		
		return null;
	}
	
	public ApplicationUserDto getCertificateDetails(Long Id, ApplicationStatus status) {
		ApplicationUserDto application = applicationRepository.findByIdAndStatus(Id,status.name());
			return application;
		
	}
	
	public List<Application> getAllCertificate(String agentEmail, ApplicationStatus verified) {
		List<Application> appList = applicationRepository.findByAgentEmailAndStatus(agentEmail,verified);		
			return appList;
		
	}
	
	public byte[] getCertificatePdf(Long Id, ApplicationStatus status) throws Exception {
		Optional<Application> applicationOpt = applicationRepository.findByApplicationIdAndStatus(Id,status);
		if(applicationOpt.isPresent()) {
//			System.out.println(application.get().getApplicantEmail() );
			Application application = applicationOpt.get();
//			byte [] pdfData = pdfGenerator.generatePdfView1(application);
//			byte [] pdfData = pdfGenerator.generatePdfView2(application);
			byte [] pdfData = pdfGenerator.generatePdfView3(application);
			return pdfData;
		}else {
			throw new ResourceNotFoundException("Application Certificate not found");
		}
	}
	
}
