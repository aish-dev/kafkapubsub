package com.sample.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.kafka.model.Message;
import com.sample.kafka.service.MessageConsumerService;
import com.sample.kafka.service.MessageProducerService;
import com.sample.kafka.utils.Response;

@RestController
@RequestMapping("message")
public class MessageController {
	
	@Autowired
	private MessageProducerService messageProducerService;
	
	@Autowired
	private MessageConsumerService messageConsumerService;
	
	@PostMapping("publish")
	public Response publishMessage(@RequestBody Message message) {
		return messageProducerService.produceMessage(message);
	}
	
	@PostMapping("consume/{id}")
	public Message consumeMessage(@PathVariable int id) {
		return messageConsumerService.getMessage(id);
	}

}
