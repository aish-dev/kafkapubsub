package com.sample.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sample.kafka.model.Message;

@Service
public class MessageConsumerService {
	
	@KafkaListener(topics = "${spring.kafka.template.default-topic}", 
			groupId = "${spring.kafka.consumer.group-id}"
			,containerFactory = "orderKafkaListenerContainerFactory")
	public Message getMessage(Message message) {
		return message;
	}
	
	
}
