package com.kingboot.timer.factory.payload;


import com.kingboot.timer.factory.payload.interfaces.JobPayloadFactory;

import java.util.Date;

/**
 * <p class="detail">
 * 功能:日期载荷生产工厂
 * </p>
 * @author Kings
 * @ClassName Abstract job.
 * @Version V1.0.
 * @date 2019.07.30 10:34:21
 */
public class DatePayloadFactory implements JobPayloadFactory<Date> {
	@Override
	public Date generatePayload() {
		return new Date();
	}
}
