package com.kchen.rocketmq.demo.consumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.kchen.rocketmq.demo.common.entity.Order;
import com.kchen.rocketmq.demo.consumer.service.OrderConsumeService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by kchen on 2017/7/2.
 */

@Service
public class OrderConsumeServiceImpl implements OrderConsumeService {

    private static Logger logger = LoggerFactory.getLogger(OrderConsumeServiceImpl.class);

    @Override
    public void consumeOrder(Order order) {
        logger.info("consumer order:{}", order);
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        MessageExt msg = msgs.get(0);
        logger.info("begin consuming, msgid:{}", msg.getMsgId());
        ConsumeConcurrentlyStatus status = ConsumeConcurrentlyStatus.RECONSUME_LATER;
        try {
            Order order = JSON.parseObject(msg.getBody(), Order.class);
            consumeOrder(order);
            status = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception ex) {
            logger.error("consume failed, exception:{}", ex);
        }
        logger.info("end consuming, msgid:{}, status:{}", msg.getMsgId(), status);
        return status;
    }

    /*@Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        Message msg = msgs.get(0);
        ConsumeOrderlyStatus status = ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        try {
            Order order = JSON.parseObject(msg.getBody(), Order.class);
            consumeOrder(order);
            status = ConsumeOrderlyStatus.SUCCESS;
        } catch (Exception ex) {

        }

        return status;
    }*/
}
