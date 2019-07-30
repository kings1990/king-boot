package com.kingboot.timer.consumer;


import com.alibaba.fastjson.JSONObject;
import com.kingboot.timer.entity.TopicInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.sql.Timestamp;

/**
 * <p class="detail">
 * 功能:
 * </p>
 * @author Kings
 * @ClassName Abstract consumer.
 * @Version V1.0.
 * @date 2019.07.30 10:31:01
 */
public abstract class AbstractConsumer implements MessageHandler {
	/** The constant TOPICINFO. */
	private static final String TOPICINFO = "TopicInfo";
	/** Mongo template. */
	@Autowired
	private MongoTemplate mongoTemplate;
	/** Group id. */
	@Value ("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Override
	public void saveExcute(ConsumerRecord<?, ?> record) {
		TopicInfo topicInfo = new TopicInfo();
		topicInfo.setTopic(record.topic());
		topicInfo.setOffset(record.offset());
		topicInfo.setLastTime(new Timestamp(record.timestamp()));
		topicInfo.setConsumer(groupId);
		try {
			JSONObject jsonObject = JSONObject.parseObject(record.value().toString());
			topicInfo.setPayload(jsonObject.getString("payload"));
		} catch (Exception e) {
			topicInfo.setPayload("--");
		}
		mongoTemplate.insert(topicInfo, TOPICINFO);
	}
	
	/**
	 * <p class="detail">
	 * 功能:监听
	 * </p>
	 * @param record :消费记录
	 *
	 * @author Kings
	 * @date 2019.07.30 10:31:01
	 */
	public abstract void listen(ConsumerRecord<?, ?> record);
}
