package com.tarena.luban.test.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.List;

public class MyConsumer {
    @Test
    public void consume() throws Exception {
    //1. 准备一个消费者对象,提供必要的属性参数
        //准备一个消费者对象
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer();
        //提供消费者对象 nameserver连接属性
        consumer.setNamesrvAddr("localhost:9876");
        //给消费者提供分组,同一个分组的消费者可以有多个消费者对象进程
        consumer.setConsumerGroup("consumer-01");
        //提供消费者监听绑定的目标主题
//        consumer.subscribe("topic-01","*");
        //提供消费者监听绑定的目标主题 明确表示,要消费的消息 tag必须=tagA，没有标签的会不会消费
        consumer.subscribe("topic-01","tagA");
    //2. 执行api方法监听主题 拿到消息消费者过程
        //consumer可以调用api方法 ,我们提供一个监听器对象 进行消息的消费
        consumer.setMessageListener(new MessageListenerConcurrently() {
            //consumerMessage方法,当消费者拿到至少一条消息之后 开始调用
            //List<MessageExt> msgs 每次这个数组中只有一个元素,就是拿到的那条消息数据
            //ConsumeConcurrentlyContext context 当前监听的消费者有关的上下文对象
            //返回值只有2中结果ConsumeConcurrentlyStatus 消费成功 success 延迟消费 reconsume_later
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //通过对list msgs解析 拿到消息,处理消费逻辑 消费成功返回success 消费失败 返回reconsume_later
                MessageExt messageExt = msgs.get(0);
                //messageExt包含发送message对象
                byte[] body=messageExt.getBody();//消息体 byte[]
                String message=new String(body, StandardCharsets.UTF_8);
                System.out.println("消费者消费接收消息:"+message);
                String topic = messageExt.getTopic();//来自于某个topic
                String bornHostString = messageExt.getBornHostString();//broker地址
                System.out.println("broker的host地址:"+bornHostString);
                //消费者的返回值决定了消息在中间件中的状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    //3. 开启连接 ,启动消费者
        consumer.start();
    //为了不让线程执行完成 消失,添加一个死循环
        while(true);
    }
}
