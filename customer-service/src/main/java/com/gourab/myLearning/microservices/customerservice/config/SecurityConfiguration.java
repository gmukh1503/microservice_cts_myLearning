package com.gourab.myLearning.microservices.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*
	 * Note:-Define in the order of Highest authorization level to Lowest. 
	 * If we declare the lowest authorization earlier then it will 
	 * never check the highest authorization. So order is very important.
	 */
	@Override   
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
	}
	
	@Bean
	public PasswordEncoder getNoOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
