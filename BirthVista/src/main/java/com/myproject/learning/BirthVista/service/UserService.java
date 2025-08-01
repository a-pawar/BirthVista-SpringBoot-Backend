package com.myproject.learning.BirthVista.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.myproject.learning.BirthVista.config.UserDetailsImpl;
import com.myproject.learning.BirthVista.dto.RegisterRequestDto;
import com.myproject.learning.BirthVista.dto.UserDto;
import com.myproject.learning.BirthVista.dto.UserUpdateDto;
import com.myproject.learning.BirthVista.enums.UserRole;
import com.myproject.learning.BirthVista.exception.ResourceNotFoundException;
import com.myproject.learning.BirthVista.model.User;
import com.myproject.learning.BirthVista.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public UserService(UserRepository userRepo,PasswordEncoder passwordEncoder) {
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	
	public boolean registerUser(RegisterRequestDto userDto) {
		if(userRepo.existsByEmail(userDto.getEmail())) {
			return false;
		}
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setAddress(userDto.getAddress());
		user.setMobileNumber(userDto.getMobileNumber());
		
		user.setRole(UserRole.ROLE_USER);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRegDate(LocalDate.now()); 
		
		userRepo.save(user);
		
		return true; 
	}

	public User loginUser(String email, String password) {
		
		Optional<User> userOpt = userRepo.findByEmail(email);
		
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
		
	}

	public boolean updateUserDetails(Long id, UserUpdateDto updatedUser) {
		Optional<User> userOpt = userRepo.findById(id);
		
		if(userOpt.isPresent()) {
			User existingUser = userOpt.get();
			
			if(updatedUser.getFirstName()!=null && updatedUser.getFirstName()!="") existingUser.setFirstName(updatedUser.getFirstName());
			if(updatedUser.getLastName()!=null && updatedUser.getLastName()!="")existingUser.setLastName(updatedUser.getLastName());
			if(updatedUser.getAddress()!=null && updatedUser.getAddress()!="")existingUser.setAddress(updatedUser.getAddress());
			if(updatedUser.getMobileNumber()!=null && updatedUser.getMobileNumber()!="")existingUser.setMobileNumber(updatedUser.getMobileNumber());

			if(updatedUser.getPassword()!=null && updatedUser.getPassword()!="") {
				existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			}
			
			userRepo.save(existingUser);
			return true;
			
		}
		return false;
	}
	
	public boolean deleteUserDetails(Long id) {
		Optional<User> userOpt = userRepo.findById(id);
		
		if(userOpt.isPresent()) {
			
			userRepo.deleteById(id);
			return true;
		}else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	public UserDto getUserDetails(Long id) {
		Optional<User> userOpt = userRepo.findById(id);
		
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			UserDto  userDto= new UserDto(user.getFirstName(),user.getLastName(),user.getEmail(),user.getMobileNumber(),
					user.getAddress(),user.getRegDate());
			return userDto;
		}else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	
		
	}

	public List<User> getAllUserDetails() {
		List<User> userList = userRepo.findAll();
		
		return userList;
	}
	
	public User getCurrentUser() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    return userDetails.getUser(); 
	}
	
	
}
