package com.leaf.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ReturnCallBack implements RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void init() {
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息标识:"+returnedMessage.getMessage().getMessageProperties().getDeliveryTag());
        String messageBody = new String(returnedMessage.getMessage().getBody(), StandardCharsets.UTF_8);
        log.error("消息体:"+messageBody);
        log.error("返回代码:"+returnedMessage.getReplyCode());
        log.error("返回文本:"+returnedMessage.getReplyText());
        log.error("交换机:"+returnedMessage.getExchange());
        log.error("路由key:"+returnedMessage.getRoutingKey());
    }
}
