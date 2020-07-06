/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gourab.myLearning.microservices.customerservice.model.Customer;

/**
 * @author GOURAB
 *
 */
@Component
public class EventPublisher {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${publisher.exchangeName}")
	private String exchangeName;
	
	@Value("${publisher.routingKey}")
	private String routingKey;
	
	//@Scheduled(fixedDelay = 2000, initialDelay = 1000)
	public void sendMessage(Customer customer) {
		try {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, customer);
		}catch(AmqpException ae) {
			System.out.println("-----SENDING -> QUEUE ERROR :--"+ae.getMessage());
		}
	}
}
