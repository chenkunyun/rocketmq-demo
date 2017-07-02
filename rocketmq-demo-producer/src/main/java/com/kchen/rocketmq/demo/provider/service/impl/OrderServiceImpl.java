package com.kchen.rocketmq.demo.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.kchen.rocketmq.demo.common.entity.Order;
import com.kchen.rocketmq.demo.provider.service.OrderService;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by kchen on 2017/7/2.
 */

@Service
@ConfigurationProperties("rocketmq.producer")
@Validated
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @NotNull
    private String topic;

    @NotNull
    private String tag;

    @Autowired
    private MQProducer mqProducer;

    @Override
    public void sendOrderToMQ(Order order) {
        Message message = new Message(topic, tag, order.getOrderId(),
                JSON.toJSONBytes(order));
        try {
            mqProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("order sent succ, msgid:{}, order:{}",
                            sendResult.getMsgId(), order);
                }

                @Override
                public void onException(Throwable e) {
                    logger.error("order sent failed, order:{}, exception:{}",
                            order, Throwables.getRootCause(e));
                }
            });
        } catch (Exception e) {
            logger.error("order sent failed, order:{}, exception:{}",
                    order, Throwables.getRootCause(e));
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        OrderServiceImpl.logger = logger;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public MQProducer getMqProducer() {
        return mqProducer;
    }

    public void setMqProducer(MQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }
}
