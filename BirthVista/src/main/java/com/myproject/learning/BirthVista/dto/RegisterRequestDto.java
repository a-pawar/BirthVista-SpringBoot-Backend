package com.myproject.learning.BirthVista.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDto {

	@NotBlank(message = "Name is required")
    private String firstName;

    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    
    private String lastName;
    private String mobileNumber;
    private String address;
    
    public RegisterRequestDto() {}
    
	public RegisterRequestDto(@NotBlank(message = "Name is required") String firstName,
			@Email(message = "Please provide a valid email") String email,
			@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String password,
			String lastName, String mobileNumber, String address) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
    
    
}
