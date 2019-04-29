package com.kingboot.timer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface MessageHandler {
	
	void saveExcute(ConsumerRecord<?, ?> record);
}