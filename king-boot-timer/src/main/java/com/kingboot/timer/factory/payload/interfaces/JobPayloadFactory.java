package com.kingboot.timer.factory.payload.interfaces;

/**
 * <p class="detail">
 * 功能:
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Job payload factory.
 * @Version V1.0.
 * @date 2019.07.30 10:31:59
 */
public interface JobPayloadFactory<T> {
	/**
	 * <p class="detail">
	 * 功能:生产载荷
	 * </p>
	 * @return t
	 * @author Kings
	 * @date 2019.07.30 10:31:59
	 */
	T generatePayload();
}
