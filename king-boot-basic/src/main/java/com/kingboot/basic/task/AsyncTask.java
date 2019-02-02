package com.kingboot.basic.task;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * <p class="detail">
 * 功能:异步任务类
 * </p>
 * @author Kings
 * @ClassName Async task.
 * @Version V1.0.
 * @date 2019.02.02 16:56:01
 */
@Component
@Async
public class AsyncTask {
    
    public Future<String> task1() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1耗时:"+(end-begin));
        return new AsyncResult<>("任务1");
    }
    
    public Future<String> task2() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("任务2耗时:"+(end-begin));
        return new AsyncResult<>("任务2");
    }
    
    public Future<String> task3() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        System.out.println("任务3耗时:"+(end-begin));
        return new AsyncResult<>("任务3");
    }
}
 