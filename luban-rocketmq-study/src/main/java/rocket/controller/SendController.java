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
         *String destination: 目的地 指的是 topic+tag标签
         *Message message: spring框架整合的message 内部包含了body序列化与返序列化过程 看不到byte[]
         *SendCallback回调: 会在发送结束后 调用回调方法
         * success回调: 发成功了
         * exception回调: 发送出现异常了.
         */
        /*rocketMQTemplate.asyncSend("test-spring-topic", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("成功发送了");
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
                System.out.println("发送消息失败了,失败信息:"+e.getMessage());
            }
        });*/
        //rocketMQTemplate.asyncSend();//异步消息发送,无法直接在同步代码中拿到返回值,速度比同步发快


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
