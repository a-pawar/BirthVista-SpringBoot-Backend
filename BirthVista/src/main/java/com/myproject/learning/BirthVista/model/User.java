package com.myproject.learning.BirthVista.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

import com.myproject.learning.BirthVista.enums.UserRole;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "reg_date")
    private LocalDate regDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name="role") // ROLE_ADMIN or ROLE_USER
    private UserRole role;
    
   @OneToMany(mappedBy="agent",cascade=CascadeType.ALL)
   private List<Application> applications;
   					
   public User () {}
   
   public User(Long id, String firstName, String lastName, String mobileNumber, String address, String email,
		String password, LocalDate regDate, UserRole role, List<Application> applications) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.mobileNumber = mobileNumber;
	this.address = address;
	this.email = email;
	this.password = password;
	this.regDate = regDate;
	this.role = role;
	this.applications = applications;
   }

   public Long getId() {
	return id;
   }

   public void setId(Long id) {
	this.id = id;
   }

   public String getFirstName() {
	return firstName;
   }

   public void setFirstName(String firstName) {
	this.firstName = firstName;
   }

   public String getLastName() {
	return lastName;
   }

   public void setLastName(String lastName) {
	this.lastName = lastName;
   }

   public String getMobileNumber() {
	return mobileNumber;
   }

   public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
   }

   public String getAddress() {
	return address;
   }

   public void setAddress(String address) {
	this.address = address;
   }

   public String getEmail() {
	return email;
   }

   public void setEmail(String email) {
	this.email = email;
   }

   public String getPassword() {
	return password;
   }

   public void setPassword(String password) {
	this.password = password;
   }

   public LocalDate getRegDate() {
	return regDate;
   }

   public void setRegDate(LocalDate regDate) {
	this.regDate = regDate;
   }

   public UserRole getRole() {
	return role;
   }

   public void setRole(UserRole role) {
	this.role = role;
   }

   public List<Application> getApplications() {
	return applications;
   }

   public void setApplications(List<Application> applications) {
	this.applications = applications;
   }

   @Override
   public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber
			+ ", address=" + address + ", email=" + email + ", password=" + password + ", regDate=" + regDate
			+ ", role=" + role + ", applications=" + applications + "]";
   }
   
   
}
