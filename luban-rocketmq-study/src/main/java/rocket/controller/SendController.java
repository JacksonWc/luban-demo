package rocket.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    /**
     * 根据笔记接口文件
     */
    @GetMapping("/send")
    public String send(String name){
        //发送消息
        /**
         * String destination: 目的地 指的是 topic+tag标签
         * Message message: spring框架整合的message 内部包含了body序列化与返序列化过程 看不到byte[]
         */
        //消息创建过程 payLoad载荷 任何object类型的消息体
        Message<String> message =
                MessageBuilder.withPayload("hello spring rocket " + name).build();
        SendResult sendResult =
                rocketMQTemplate.
                        syncSend("test-spring-topic", message);//同步消息发送


        /**
         *

         *
         *
         *
         *          *  //延迟消息发送 允许发送消息的时候 api方法参数 设置延迟级别 1 2 3 4 5 18 根据不同级别 消息
         *          *         //出现在队列的时间滞后时间不同
         *          *         /**
         *          *          * String destination: 目的地 指的是 topic+tag标签
         *          *          * Message message: spring框架整合的message 内部包含了body序列化与返序列化过程 看不到byte[]
         *          *          * timeout: 单位毫秒的 底层连接超时时间 默认3000
         *          *          * delayLevel: 1-18
         *          *
         rocketMQTemplate.syncSend("test-spring-topic", message, 3000, 4);


         */



        return "success";
    }
}
