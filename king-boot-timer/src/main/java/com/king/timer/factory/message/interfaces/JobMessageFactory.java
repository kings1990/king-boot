package com.king.timer.factory.message.interfaces;

public interface JobMessageFactory<T> {
    String generateMessage(T payload);
}
