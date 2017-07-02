package com.kchen.rocketmq.demo.consumer.service;

import com.kchen.rocketmq.demo.common.entity.Order;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

public interface OrderConsumeService extends MessageListenerConcurrently {
    void consumeOrder(Order order);
}
