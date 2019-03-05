package com.kingboot.timer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p class="detail">
 * 功能:kafka测试接收
 * </p>
 * @author Kings
 * @ClassName Kafka receiver.
 * @Version V1.0.
 * @date 2019.02.04 15:07:10
 */
@Component
@Slf4j
public class KafkaReceiver extends AbstractConsumer {
    
    @Override
    @KafkaListener (topics = {"test2", "test1"})
    public void listen(ConsumerRecord<?, ?> record) {
        Object value = record.value();
        Optional<?> kafkaMessage = Optional.ofNullable(value);
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("record =" + record);
            log.info("message =" + message);
            super.saveExcute(record);
        }
        
        
    }
}