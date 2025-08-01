package com.myproject.learning.BirthVista.dto;

import java.time.LocalDate;

public class UserDto {
	
	
	private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;
    private LocalDate RegDate;
    
    
	public UserDto() {
	}


	public UserDto(String firstName, String lastName,String email, String mobileNumber, String address,
			LocalDate regDate) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		RegDate = regDate;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public LocalDate getRegDate() {
		return RegDate;
	}


	public void setRegDate(LocalDate regDate) {
		RegDate = regDate;
	}

    
}
