/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gourab.myLearning.microservices.customerservice.config.EventPublisher;
import com.gourab.myLearning.microservices.customerservice.model.Customer;
import com.gourab.myLearning.microservices.customerservice.model.Customers;
import com.gourab.myLearning.microservices.customerservice.util.InvalidCustomerException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author GOURAB
 *
 */
@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EventPublisher eventPublisher;
	
	/**
	 * @return
	 */
	public ResponseEntity<Customers> getAllCustomers() {
		ResponseEntity<Customers> respEntity=null;
		List<Customer> customerList = customerRepo.findAll();
		Customers allCustomers = new Customers();
		allCustomers.setCustomerList(customerList);
		if(null!=customerList && customerList.size()>0) {
			respEntity = new ResponseEntity<Customers>(allCustomers, HttpStatus.FOUND);
		}else {
			respEntity = new ResponseEntity<Customers>(allCustomers, HttpStatus.NOT_FOUND);
		}
		return respEntity;
	}

	/**
	 * @param customerReq
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "createACustomerFallback")
	public Customer createACustomer(Customer customerReq) {
		Customer custResp = null;
		if (null != customerReq 
				&& null != customerReq.getFirstName() && !customerReq.getFirstName().isEmpty()
				&& null != customerReq.getLastName() && !customerReq.getLastName().isEmpty()
				&& null != customerReq.getEmail() && !customerReq.getEmail().isEmpty()) {
			custResp = customerRepo.saveAndFlush(customerReq);

		} else {
			throw new InvalidCustomerException("Please provide valid Customer Information.");
		}
		return custResp;
	}

	/**
	 * @param customerReq
	 * @return
	 */
	public Customer createACustomerFallback(Customer customerReq){
		Customer customerResp = new Customer();
		customerResp.setFirstName(customerReq.getFirstName());
		customerResp.setLastName(customerReq.getLastName());
		customerResp.setEmail(customerReq.getEmail());
		return customerResp;
	}

	/**
	 * @param customerResp
	 */
	public void publishEvent(Customer customerResp) {
		eventPublisher.sendMessage(customerResp);
	}
}
