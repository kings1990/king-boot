package com.king.timer.job.template;

import com.king.timer.factory.message.DateMessageFactory;
import com.king.timer.factory.payload.DatePayloadFactory;
import com.king.timer.interfaces.AbstractJob;

import java.util.Date;

/**
 * <p class="detail">
 * 功能: 触发yyyy-MM-dd HH:mm:ss格式的消息，如 2017-04-28 09:54:37
 * </p>
 * @author hbprotoss
 * @ClassName Date time message job.
 * @Version V1.0.
 * @date 2017.04.28 09:54:37
 */
public class DateTimeMessageJob extends AbstractJob<Date> {
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        setJobMessageFactory(new DateMessageFactory(DateMessageFactory.DATETIME_FORMAT));
        setJobPayloadFactory(new DatePayloadFactory());
    }
}
