/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * @author GOURAB
 *
 */
//@Configuration
public class FeignConfiguration {

	@Value("${spring.security.user.name}")
	private String userName;
	
	@Value("${spring.security.user.password}")
	private String password; 
	
	/*
	 * @Bean public Contract feignContract() { return new feign.Contract.Default();
	 * }
	 */
	
	@Bean
	public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(userName,password);
	}
	
	/*
	 * @Bean public Feign getFeign() { return
	 * Feign.builder().requestInterceptor(getBasicAuthRequestInterceptor()).build();
	 * }
	 */
}
