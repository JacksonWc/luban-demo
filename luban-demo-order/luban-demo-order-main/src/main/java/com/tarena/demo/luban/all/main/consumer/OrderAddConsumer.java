package com.tarena.demo.luban.all.main.consumer;

import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RocketMQMessageListener(topic="luban-demo-order-topic",consumerGroup = "order-add-consumer")
public class OrderAddConsumer implements RocketMQListener<OrderAddParam> {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(OrderAddParam message) {

        log.info("订单新增接收消费对象:{}",message);
        //生成 锁的key值 和随机value值
        String lockKey="order:add:"+message.getOrderSn()+".lock";
        String rand= UUID.randomUUID().toString();
        //执行抢锁 api 底层 setnx+expire
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        //setnx lockKey rand && expire lockKey 10
        Boolean tryLock = opsForValue.setIfAbsent(lockKey, rand, 10, TimeUnit.SECONDS);
        log.info("抢锁结果:{}",tryLock);
        //没有抢到锁
        //循环只要tryLock是false 一直执行 抢锁 判断
        while(!tryLock){
            log.info("抢锁失败 等待五秒");
            //等待5秒钟
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tryLock = opsForValue.setIfAbsent(lockKey, rand, 10, TimeUnit.SECONDS);
        }
        orderService.addOrder(message);
        //手动释放 判断是否是当前线程的锁
        String lockValue = opsForValue.get(lockKey);
        if (lockKey!=null && lockValue.equals(rand)){
            //说明当前锁 是可以被释放的
            stringRedisTemplate.delete(lockKey);
        }
    }
}
