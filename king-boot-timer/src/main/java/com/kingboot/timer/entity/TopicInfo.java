package com.kingboot.timer.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p class="detail">
 * 功能:执行者信息（groupID 和上次实际执行时间）
 * </p>
 * @author Kings
 * @ClassName Topic info.
 * @Version V1.0.
 * @date 2019.02.04 10:39:32
 */
@Getter
@Setter
public class TopicInfo {
    /** The constant dateFormat. */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** Consumer. */
    private String consumer;
    /** Last time. */
    private Date lastTime;
    private Long offset;
    private String topic;
    
    private String payload;
    
    /**
     * Instantiates a new Topic info.
     * @param consumer the consumer
     */
    public TopicInfo(String consumer, String topic, Long offset, Date lastTime) {
        this.consumer = consumer;
        this.topic = topic;
        this.offset = offset;
        this.lastTime = lastTime;
    }
    
    public TopicInfo() {
    }
}
