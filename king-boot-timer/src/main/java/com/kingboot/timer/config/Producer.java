package com.kingboot.timer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * <p class="detail">
 * 功能:kafka生产者
 * </p>
 * @author Kings
 * @ClassName Producer
 * @Version V1.0.
 * @date 2019.07.30 10:37:51
 */
public class Producer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private KafkaTemplate<String, GenericMessage> kafkaTemplate;
	
	@Async
	public void send(String topic, GenericMessage message) {
		ListenableFuture<SendResult<String, GenericMessage>> future = kafkaTemplate.send(topic, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, GenericMessage>>() {
			
			@Override
			public void onSuccess(final SendResult<String, GenericMessage> message) {
				LOGGER.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
			}
			
			@Override
			public void onFailure(final Throwable throwable) {
				LOGGER.error("unable to send message= " + message, throwable);
			}
		});
	}
}