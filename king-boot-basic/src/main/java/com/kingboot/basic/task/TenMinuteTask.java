package com.kingboot.basic.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p class="detail">
 * 功能:每10分钟执行
 * </p>
 * @author Kings
 * @ClassName Ten minute task.
 * @Version V1.0.
 * @date 2019.02.02 16:49:06
 */
@Component
public class TenMinuteTask {
    
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void excute(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
