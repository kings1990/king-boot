package com.kingboot.timer.factory.message.interfaces;

/**
 * <p class="detail">
 * 功能:
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Job message factory.
 * @Version V1.0.
 * @date 2019.07.30 10:31:29
 */
public interface JobMessageFactory<T> {
	/**
	 * <p class="detail">
	 * 功能:生产消息
	 * </p>
	 * @param payload :payload
	 *
	 * @return string
	 * @author Kings
	 * @date 2019.07.30 10:31:29
	 */
	String generateMessage(T payload);
}
