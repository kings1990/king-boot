package com.king.timer.factory.payload;


import com.king.timer.factory.payload.interfaces.JobPayloadFactory;

import java.util.Date;

public class DatePayloadFactory implements JobPayloadFactory<Date> {
    @Override
    public Date generatePayload() {
        return new Date();
    }
}
