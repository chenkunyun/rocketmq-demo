package com.kchen.rocketmq.demo.provider.config.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
@Validated
public class RabbitmqConfig {

    @NotEmpty
    private String namesrvAddr;

    @NotEmpty
    private String groupName;

    @Range(min = 1)
    private int maxMessageSizeBytes;

    @Range(min = 1)
    private int sendMsgTimeoutSecond;

    @NotEmpty
    private String instanceName;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer producer() {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);

        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(maxMessageSizeBytes);
        producer.setSendMsgTimeout(sendMsgTimeoutSecond);

        return producer;
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

    public void setMaxMessageSizeBytes(Integer maxMessageSizeBytes) {
        this.maxMessageSizeBytes = maxMessageSizeBytes;
    }

    public int getMaxMessageSizeBytes() {
        return maxMessageSizeBytes;
    }

    public void setMaxMessageSizeBytes(int maxMessageSizeBytes) {
        this.maxMessageSizeBytes = maxMessageSizeBytes;
    }

    public int getSendMsgTimeoutSecond() {
        return sendMsgTimeoutSecond;
    }

    public void setSendMsgTimeoutSecond(int sendMsgTimeoutSecond) {
        this.sendMsgTimeoutSecond = sendMsgTimeoutSecond;
    }

    public void setSendMsgTimeoutSecond(Integer sendMsgTimeoutSecond) {
        this.sendMsgTimeoutSecond = sendMsgTimeoutSecond;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
