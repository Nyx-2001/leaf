package com.leaf.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 当消息没有到达Rabbitmq的交换机时触发该方法（当然到达了也会触发，）
 */
@Slf4j
@Component
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     *
     * @param correlationData 消息属性体
     * @param ack 是否成功，成功到达true，没有到达，false
     * @param cause rabbitmq自身给的信息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //如果发送端发送消息时没有对correlationData进行处理，conirm方法接收到的对象都会是null
        //当接收失败并且correlationData对象为null，证明目前已经无法追溯回业务，可以做业务日志处理
        if( !ack && correlationData == null){
            log.error(cause);
            return;
        }
        //如果接收失败
        if(!ack){
            log.error("消息Id:"+correlationData.getId());
            log.error("消息体:"+new String(correlationData.getReturned().getMessage().getBody()));
            //这里可以持久化业务消息体到数据库，然后定时去进行补偿处理或者重试等等
            return;
        }
        //处理完成
    }
}
