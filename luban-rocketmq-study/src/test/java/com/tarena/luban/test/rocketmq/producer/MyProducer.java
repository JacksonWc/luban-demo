package com.tarena.luban.test.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.*;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MyProducer {
    @Test
    public void send() throws Exception {
    //1.准备好一个可以连接nameserver服务器的生产者对象
        DefaultMQProducer producer= new DefaultMQProducer();
        //配置nameserver连接属性
        producer.setNamesrvAddr("localhost:9876");
        //给生产者设置分组 同一组 下可能有多个producer进程
        producer.setProducerGroup("prod-test01");
        //启动生产者
        producer.start();
    //2.封装准备生产者要发送的消息
        //创建一个消息对象
        Message message=new Message();

        //绑定一个key值
        List<String> keys=new ArrayList<>();
        keys.add("abc");
        keys.add("cba");
        message.setKeys(keys);
        //设置tag标签 在消费端对标签进行过滤消费
        message.setTags("tagA");


        //准备一个消息包装的文本
        String msgTxt="欢迎来到rocketmq";
        //消息体 保存消息文本byte数组
        message.setBody(msgTxt.getBytes(StandardCharsets.UTF_8));
        //设置消息队列主题 目前rocketmq没有任何主题,所以自定义主题会自动创建
        message.setTopic("topic-01");
    //3.调用发送的api方法 获取返回结果
        SendResult result = producer.send(message);
        //解析result对象的一些数据的
        System.out.println("消息发送状态:"+result.getSendStatus());
    }
}
