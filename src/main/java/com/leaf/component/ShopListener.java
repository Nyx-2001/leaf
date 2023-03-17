package com.leaf.component;

import com.leaf.service.IShopService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

import static com.leaf.utils.MqConstants.*;

/**
 * @Author Hanami
 * @Date 2023-03-01
 */

@Component
public class ShopListener {

    @Resource
    private IShopService shopService;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(SHOP_EXCHANGE),
            key = SHOP_INSERT_KEY,
            value = @Queue(value = SHOP_INSERT_QUEUE, durable = "true")
    ))
    public void listenShopInsertOrUpdate (Long id, @Headers Map<String, Object> headers, Channel channel) throws IOException {
         shopService.insertOrUpdateBy(id);
         channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(SHOP_EXCHANGE),
            key = SHOP_DELETE_KEY,
            value = @Queue(value = SHOP_DELETE_QUEUE, durable = "true")
    ))
    public void listenShopDelete(Long id, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        shopService.deleteById(id);
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
    }
}
