/**
 * 
 */
package com.gourab.myLearning.microservices.customerservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GOURAB
 *
 */
@Configuration
public class RabbitMQConfiguration {

	@Value("${publisher.queue}")
	private String queueName;
	
	@Value("${publisher.exchangeName}")
	private String exchangeName;
	
	@Value("${publisher.routingKey}")
	private String routingKey;
	
	@Bean
	public Queue getQueue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	public Binding getBinding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	
	@Bean 
	public DirectExchange getExchange() {		
		return new DirectExchange(exchangeName);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	/*
	 * @Bean public AmqpTemplate getRabbitTemplate(ConnectionFactory
	 * connectionFactory) { final RabbitTemplate rabbitTemplate = new
	 * RabbitTemplate(connectionFactory); return rabbitTemplate; }
	 */
}
