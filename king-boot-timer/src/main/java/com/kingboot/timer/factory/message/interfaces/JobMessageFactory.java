package com.kingboot.timer.factory.message.interfaces;

public interface JobMessageFactory<T> {
    String generateMessage(T payload);
}
