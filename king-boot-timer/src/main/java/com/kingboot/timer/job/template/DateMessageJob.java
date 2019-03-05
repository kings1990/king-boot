package com.kingboot.timer.job.template;

import com.kingboot.timer.factory.message.DateMessageFactory;
import com.kingboot.timer.factory.payload.DatePayloadFactory;
import com.kingboot.timer.interfaces.AbstractJob;

import java.util.Date;


/**
 * <p class="detail">
 * 功能: 触发yyyy-MM-dd格式的消息, 如 2017-04-28
 * </p>
 * @author hbprotoss
 * @ClassName Date message job.
 * @Version V1.0.
 * @date 2017.04.28 09:53:49
 */
public class DateMessageJob extends AbstractJob<Date> {
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        setJobMessageFactory(new DateMessageFactory(DateMessageFactory.DATE_FORMAT));
        setJobPayloadFactory(new DatePayloadFactory());
    }
}
