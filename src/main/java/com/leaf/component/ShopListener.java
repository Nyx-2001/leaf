package com.leaf.component;

import com.leaf.service.IShopService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
            value = @Queue(SHOP_INSERT_QUEUE)
    ))
    public void listenShopInsertOrUpdate (Long id) {
         shopService.insertOrUpdateBy(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(SHOP_EXCHANGE),
            key = SHOP_DELETE_KEY,
            value = @Queue(SHOP_DELETE_QUEUE)
    ))
    public void listenShopDelete(Long id) {
        shopService.deleteById(id);
    }
}
