package com.kingboot.timer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * <p class="detail">
 * 功能:
 * </p>
 * @author Kings
 * @ClassName Message handler.
 * @Version V1.0.
 * @date 2019.07.30 10:33:02
 */
public interface MessageHandler {
	
	/**
	 * <p class="detail">
	 * 功能:保存执行记录
	 * </p>
	 * @param record :记录
	 *
	 * @author Kings
	 * @date 2019.07.30 10:33:02
	 */
	void saveExcute(ConsumerRecord<?, ?> record);
}