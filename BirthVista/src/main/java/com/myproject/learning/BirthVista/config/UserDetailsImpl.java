package com.myproject.learning.BirthVista.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.myproject.learning.BirthVista.model.User;

public class UserDetailsImpl implements UserDetails {

	private User user;
	public UserDetailsImpl(User user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority  simpleGrantedAuthority=new SimpleGrantedAuthority(user.getRole().name());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	public Long getId() {
		return user.getId();
	}
	
	 public User getUser() {
	        return user;
	    }

}
