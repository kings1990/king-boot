package com.kingboot.timer.interfaces;

import com.kingboot.timer.config.Producer;
import com.kingboot.timer.config.Topics;
import com.kingboot.timer.factory.message.interfaces.JobMessageFactory;
import com.kingboot.timer.factory.payload.interfaces.JobPayloadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.support.GenericMessage;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * <p class="detail">
 * 功能:抽象job
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Abstract job.
 * @Version V1.0.
 * @date 2019.07.30 10:34:21
 */
public class AbstractJob<T> implements com.kingboot.timer.interfaces.JobInterface, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(AbstractJob.class);
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private Producer producer;
	
	private JobMessageFactory<T> jobMessageFactory;
	
	private JobPayloadFactory<T> jobPayloadFactory;
	
	private Topics topic;
	
	@Override
	public void run() {
		String message = jobMessageFactory.generateMessage(jobPayloadFactory.generatePayload());
		producer.send(topic.getValue(), new GenericMessage<>(message));
		logger.info("Message {} to topic {} sent", message, topic.getValue());
	}
	
	public void setJobMessageFactory(JobMessageFactory<T> jobMessageFactory) {
		this.jobMessageFactory = jobMessageFactory;
	}
	
	public void setJobPayloadFactory(JobPayloadFactory<T> jobPayloadFactory) {
		this.jobPayloadFactory = jobPayloadFactory;
	}
	
	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
	
	}
}
