package com.myproject.learning.BirthVista.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class AppConfig{
	
	public static final String[] PUBLIC_URLS = {
				"/api/v1/auth/**",
				"/v3/api-docs",
				"/v2/api-docs",
				"/swagger-resources/**",
				"/swagger-ui/**",
				"/webjars/**",
	};
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	               .authenticationProvider(authProvider())
	               .build();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer->customizer.disable() );
		http.authorizeHttpRequests(request -> request.requestMatchers(PUBLIC_URLS).permitAll()
														.requestMatchers("/", "/index.html", "/js/**", "/css/**", "/webjars/**").permitAll()
														.requestMatchers("/admin/**").hasRole("ADMIN")
														.requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
														.requestMatchers("/**").permitAll());
		http.formLogin(Customizer.withDefaults());		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
	
}
