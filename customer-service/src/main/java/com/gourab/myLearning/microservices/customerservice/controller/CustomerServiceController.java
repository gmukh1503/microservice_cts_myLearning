/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gourab.myLearning.microservices.customerservice.model.Customer;
import com.gourab.myLearning.microservices.customerservice.model.Customers;
import com.gourab.myLearning.microservices.customerservice.service.CustomerService;

/**
 * @author GOURAB
 *
 */
@RestController
@RequestMapping("/service1")
@EnableCircuitBreaker
public class CustomerServiceController {
	
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<Customers> fetchAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> createACustomer(@RequestBody Customer customerReq){
		ResponseEntity<Customer> respEntity = null;
		Customer customerResp = customerService.createACustomer(customerReq);
		if(null!=customerResp && null!=customerResp.getCustomerId()) {
			respEntity = new ResponseEntity<Customer> (customerResp,HttpStatus.CREATED);
			customerService.publishEvent(customerResp);
		}
		else {
			respEntity = new ResponseEntity<Customer> (customerResp,HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}
}
