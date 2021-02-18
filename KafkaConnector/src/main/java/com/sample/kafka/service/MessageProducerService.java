package com.sample.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.sample.kafka.model.Message;
import com.sample.kafka.utils.Response;

@Service
public class MessageProducerService {
	
	@Autowired
	Response response;
	
	@Value("${spring.kafka.template.default-topic}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<String, Message> sendDetailskafkaTemplate;
	
	public Response produceMessage(Message order) {
		
		ListenableFuture<SendResult<String, Message>> future = sendDetailskafkaTemplate.send(topic,order);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {

		    @Override
		    public void onSuccess(SendResult<String, Message> result) {
		    	response.setStatus("success");
		    	response.setMessage("Successfully submitted order details");

		    }

		    @Override
		    public void onFailure(Throwable ex) {
		    	response.setMessage("Failed to submit order details");
		    	response.setStatus("failure");
		    	
		    }

		});
		
		return response;
	}

}
