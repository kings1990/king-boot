package com.kingboot.timer.factory.payload.interfaces;

public interface JobPayloadFactory<T> {
    T generatePayload();
}
