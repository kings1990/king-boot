package com.kingboot.timer.consumer;


import com.alibaba.fastjson.JSONObject;
import com.kingboot.timer.entity.TopicInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.sql.Timestamp;

public abstract class AbstractConsumer implements MessageHandler {
    private static final String TOPICINFO = "TopicInfo";
    @Autowired
    private MongoTemplate mongoTemplate;
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
        } catch (Exception e){
            topicInfo.setPayload("--");
        }
        mongoTemplate.insert(topicInfo, TOPICINFO);
    }
    
    public abstract void listen(ConsumerRecord<?, ?> record);
}