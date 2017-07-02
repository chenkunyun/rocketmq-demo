package com.kchen.rocketmq.demo.consumer.config.service;


import com.kchen.rocketmq.demo.consumer.service.OrderConsumeService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties("rocketmq.consumer")
@Validated
public class RabbitMqConfig {

    @NotEmpty
    private String namesrvAddr;

    @NotEmpty
    private String groupName;

    @NotEmpty
    private String instanceName;

    @NotEmpty
    private String topic;

    @NotEmpty
    private String tag;

    @Range(min = 1)
    private int consumeThreadMin;

    @Range(min = 1)
    private int consumeThreadMax;

    @Autowired
    private OrderConsumeService orderConsumeService;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer consumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setInstanceName(instanceName);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);

        try {
            /**
             * topic - topic to subscribe.
               subExpression - subscription expression.it only support or operation such as "tag1 || tag2 || tag3"
                               if null or * expression,meaning subscribe all
             */
            consumer.subscribe(topic, tag);
        } catch (MQClientException e) {
            throw new IllegalStateException("failed to subscribe [topic:tag]-" + topic + ":" + tag);
        }

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(orderConsumeService);

        return consumer;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
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

    public int getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public int getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }
}
