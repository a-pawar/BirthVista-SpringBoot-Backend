package com.myproject.learning.BirthVista.dto;

import jakarta.validation.constraints.Size;

public class UserUpdateDto {

	
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;
    
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

	public UserUpdateDto() {
		
	}

	public UserUpdateDto(String firstName, String lastName, String mobileNumber, String address,
			@Size(min = 6, message = "Password must be at least 6 characters long") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
