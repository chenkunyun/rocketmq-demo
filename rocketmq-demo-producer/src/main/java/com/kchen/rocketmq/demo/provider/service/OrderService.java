package com.kchen.rocketmq.demo.provider.service;

import com.kchen.rocketmq.demo.common.entity.Order;

/**
 * Created by kchen on 2017/7/2.
 */
public interface OrderService {

    /**
     * 发送订单信息到mq
     * @param order
     */
    void sendOrderToMQ(Order order);
}
