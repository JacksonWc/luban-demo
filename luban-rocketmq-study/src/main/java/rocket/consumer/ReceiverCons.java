package rocket.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(
        topic = "test-spring-topic",
        consumerGroup = "aaa",
        selectorExpression = "*")
public class ReceiverCons implements RocketMQListener<String> {
    /**
     * @param message 当初发送的message包装的payLoad
     */
    @Override
    public void onMessage(String message) {
        System.out.println("spring整合的消费者消费接收:"+message);
    }
}
