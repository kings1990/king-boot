// package com.king.timer.job;
//
// import com.gttown.common.support.mq.Channels;
// import com.gttown.common.support.mq.MessageQueue;
// import com.king.timer.interfaces.JobInterface;
//
// import javax.annotation.Resource;
//
// /**
//  * Created by hbprotoss on 29/11/2016.
//  */
// public class HeartBeat implements JobInterface {
//
//     @Resource
//     private MessageQueue mq;
//
//     @Override
//     public void run() {
//         mq.publish(Channels.TIMER_HEART_BEAT, "");
//     }
// }
