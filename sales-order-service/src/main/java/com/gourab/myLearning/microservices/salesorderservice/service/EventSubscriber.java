/**
 * 
 */
package com.gourab.myLearning.microservices.salesorderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gourab.myLearning.microservices.salesorderservice.model.Customer;

/**
 * @author GOURAB
 *
 */
@Service
public class EventSubscriber {

	private Logger logger = LoggerFactory.getLogger(EventSubscriber.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@RabbitListener(queues = {"customer-created-queue"})
	public void receive(String customerString) throws JsonMappingException, JsonProcessingException {
		Customer customer = objectMapper.readValue(customerString, Customer.class);
		logger.info("Received message '{}'", customer);
		salesOrderService.saveCustomer(customer);
	}
}
