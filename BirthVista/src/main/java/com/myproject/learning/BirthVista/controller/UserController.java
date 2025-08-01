package com.myproject.learning.BirthVista.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.learning.BirthVista.dto.RegisterRequestDto;
import com.myproject.learning.BirthVista.dto.UserDto;
import com.myproject.learning.BirthVista.dto.UserUpdateDto;
import com.myproject.learning.BirthVista.model.User;
import com.myproject.learning.BirthVista.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@Tag(name = "User API")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/")
	public String Demo() {
		return "Hello BirthVista";
	}
	
//	@GetMapping("/csrf-token")
//	public CsrfToken getCsrfToken(HttpServletRequest request) {
//		return (CsrfToken) request.getAttribute("_csrf");
//	}
	
	@Operation(summary = "Register User", description = "Register User in the application")
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequestDto userDto){
		
		boolean result = userService.registerUser(userDto);
		
		if(result) {
			return ResponseEntity.ok("User Registered Successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Registered or Registration Failed");
		
	}
	
	@Operation(summary = "Update User Details", description = "Update Profile Functionality to update user details")
	@PutMapping("/user/editUser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto updatedUser){
		
		User currentUser = userService.getCurrentUser();
		Long loggedInUserId = currentUser.getId();

	    if (!loggedInUserId.equals(id)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                             .body("Access denied! You can only update your own profile.");
	    }
		
		boolean result = userService.updateUserDetails(id,updatedUser);
		
		if(result) {
			return ResponseEntity.ok("User Profile Updated Successfully!");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went Wrong!!");
		
	}
	
	@Operation(summary = "Delete User Details", description = "Delete Profile Functionality to Delete user details")
	@DeleteMapping("/user/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		
		User currentUser = userService.getCurrentUser();
		Long loggedInUserId = currentUser.getId();

	    if (!loggedInUserId.equals(id)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                             .body("Access denied! You can only delete your own profile.");
	    }
		boolean result = userService.deleteUserDetails(id);
		
		if(result) {
			return ResponseEntity.ok("User Profile Deleted Successfully!");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went Wrong!!");
		
	}
	
	
	@Operation(summary = "Get User using id", description = "Get user details using its id")
	@GetMapping("/user/getUser/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {

		User currentUser = userService.getCurrentUser();
		Long loggedInUserId = currentUser.getId();
		
	    if (!loggedInUserId.equals(id)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                             .body("Access denied! You can Access Your Own Profile Only");
	    }

		UserDto userDto = userService.getUserDetails(id);
		
		if(userDto != null) {
			
			return ResponseEntity.ok(userDto);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found!!");
	}
	
	@Operation(summary = "Get All Users", description = "Get all user details for admin view")
	@GetMapping("/admin/getAllUsers")
	public ResponseEntity<?> getAllUser() {
		
		List<User> userList = userService.getAllUserDetails();
		
		for(User user : userList) {
			user.setPassword(null);
		}
		return ResponseEntity.ok(userList);
	}
	
}


























