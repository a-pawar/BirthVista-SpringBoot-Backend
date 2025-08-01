package com.myproject.learning.BirthVista.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.learning.BirthVista.model.User;
import com.myproject.learning.BirthVista.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService  {

	private UserRepository userRepo;
	
	@Autowired
	public MyUserDetailsService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
                return new UserDetailsImpl(user) ;
        }else {
        		throw new UsernameNotFoundException("User Not found 404");
        }
	}
}
