package com.king.timer.factory.payload.interfaces;

public interface JobPayloadFactory<T> {
    T generatePayload();
}
