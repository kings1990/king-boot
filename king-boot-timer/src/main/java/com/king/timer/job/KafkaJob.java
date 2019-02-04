package com.king.timer.job;

import com.king.timer.config.Producer;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p class="detail">
 * 功能:kafka定时器
 * </p>
 * @author Kings
 * @ClassName Kafka job.
 * @Version V1.0.
 * @date 2018.04.19 16:05:42
 */
@Component
public class KafkaJob implements Job {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaJob.class);
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String topic = (String) jobDetail.getJobDataMap().get("topic");
        Producer producer = (Producer) jobDetail.getJobDataMap().get("producer");
        FORMATTER = (SimpleDateFormat) jobDetail.getJobDataMap().get("sdf");
        String message = FORMATTER.format(new Date());
        producer.send(topic, new GenericMessage<>(message));
        logger.info("Message {} to topic {} sent", message, topic);
    }
}
