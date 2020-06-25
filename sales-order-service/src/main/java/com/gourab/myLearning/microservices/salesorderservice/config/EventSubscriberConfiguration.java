package com.gourab.myLearning.microservices.salesorderservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gourab.myLearning.microservices.salesorderservice.service.EventSubscriber;

@Profile("subscriber")
@Configuration
public class EventSubscriberConfiguration {

	@Value("${subscriber.queue}")
	private String queueName;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
	

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(EventSubscriber eventSubscriber, MessageConverter messageConverter) {
		MessageListenerAdapter messageListenerAdapter =  new MessageListenerAdapter(eventSubscriber, "receive");
		return messageListenerAdapter;
	}

	@Bean
	public EventSubscriber eventReceiver() {
		return new EventSubscriber();
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}