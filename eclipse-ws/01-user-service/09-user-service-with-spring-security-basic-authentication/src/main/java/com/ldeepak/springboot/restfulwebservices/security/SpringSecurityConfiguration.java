package com.ldeepak.springboot.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// All requests should be authenticated.
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		
		// If the page is not authenticated, a dialog box to enter credentials is shown.
		http.httpBasic(withDefaults());
		
		// CSRF -> POST, PUT
		http.csrf().disable();
		
		return http.build();
	}
}
