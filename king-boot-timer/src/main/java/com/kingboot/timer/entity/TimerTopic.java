package com.kingboot.timer.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimerTopic {
    
    private String _id;
    
    
    private String topic;
    
    private String createTime;
    
    /**
     * 是否删除 （true：删除 false：未删除）
     */
    private Boolean delStatus = false;
    
    
}
