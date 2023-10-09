package com.tarena.demo.luban.all.main.consumer;

import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic="luban-demo-order-topic",consumerGroup = "order-add-consumer")
public class OrderAddConsumer implements RocketMQListener<OrderAddParam> {
    @Autowired
    private OrderService orderService;
    @Override
    public void onMessage(OrderAddParam message) {
        log.info("订单新增接收消费对象:{}",message);
        for(int i=0;i<10;i++){

        }
        orderService.addOrder(message);
    }
}
