package com.myproject.learning.BirthVista.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.learning.BirthVista.dto.ApplicationUserDto;
import com.myproject.learning.BirthVista.enums.ApplicationStatus;
import com.myproject.learning.BirthVista.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	
	Optional<Application> findByApplicationIdAndStatus(Long Id,ApplicationStatus status);

	Optional<List<Application>> findByAgentEmail(String agentEmail);

	Optional<List<Application>> findByStatus(ApplicationStatus status);
	
	List<Application> findByAgentEmailAndStatus(String agentEmail, ApplicationStatus status);
	
	@Query(value = "SELECT * FROM applications WHERE application_id = :applicationId", nativeQuery = true)
    Optional<Application> getAppDetailsById(@Param("applicationId") long applicationId);

	@Query(value = """
	        SELECT a.application_id AS applicationId, 
	               a.status AS status,
	               a.full_name AS applicantName,
	               a.father_name AS applicantFatherName,
	               a.remark AS remark,
	               u.first_name AS agentfirstName,
	               u.last_name AS agentlastName,
	               u.mobile_number AS agentmobileNumber
	        FROM applications a
	        JOIN users u ON a.agent_email = u.email
	        WHERE application_id = :applicationId
	        AND a.status= :status
	        """, nativeQuery = true)
    ApplicationUserDto findByIdAndStatus(@Param("applicationId") Long applicationId,@Param("status") String status);
}






