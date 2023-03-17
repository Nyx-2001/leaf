package com.leaf.utils;

/**
 * @Author Hanami
 * @Date 2023-03-01
 */
public class MqConstants {

    /**
     * 交换机
     */
    public static final String SHOP_EXCHANGE = "shop.topic";

    public static final String ORDER_EXCHANGE = "order.topic";

    /**
     * 监听订单队列
     */
    public static final String ORDER_QUEUE = "order.queue";

    /**
     * 订单key
     */
    public static final String ORDER_KEY = "order.key";

    /**
     * 监听新增和修改的消息队列
     */
    public static final String SHOP_INSERT_QUEUE = "shop.insert.queue";

    /**
     * 监听删除的消息队列
     */
    public static final String SHOP_DELETE_QUEUE = "shop.delete.queue";

    /**
     * 新增和修改的RoutingKey
     */
    public static final String SHOP_INSERT_KEY = "shop.insert.key";

    /**
     * 删除的RoutingKey
     */
    public static final String SHOP_DELETE_KEY = "shop.delete.key";
}
