package com.kingboot.timer.factory.payload;


import com.kingboot.timer.factory.payload.interfaces.JobPayloadFactory;

import java.util.Date;

public class DatePayloadFactory implements JobPayloadFactory<Date> {
    @Override
    public Date generatePayload() {
        return new Date();
    }
}
