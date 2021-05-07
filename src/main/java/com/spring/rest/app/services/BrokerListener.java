package com.spring.rest.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BrokerListener {
	
	
	private static final Logger log = LoggerFactory.getLogger(BrokerListener.class);

	
	@KafkaListener(topics ="devs4j-topic", groupId ="alexis-group")
	public void listen(String message) {
		log.info("Message received : {}", message);
	}
}
