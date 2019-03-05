package com.kingboot.timer.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p class="detail">
 * 功能:执行者
 * </p>
 * @author Kings
 * @ClassName Topic info.
 * @Version V1.0.
 * @date 2019.02.04 10:39:32
 */
@Getter
@Setter
public class Executor {
    private List<TopicInfo> topicInfos;
    private String lastExpectTime;
    
    public static void main(String[] args) {
        Executor executor = new Executor();
        String lastExpectTime = "aaa";
        executor.setLastExpectTime(lastExpectTime);
    }
}
