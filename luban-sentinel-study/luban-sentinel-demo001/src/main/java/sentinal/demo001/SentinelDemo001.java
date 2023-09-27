package sentinal.demo001;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelDemo001 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelDemo001.class, args);
        //为当前项目 提供一个sayHello资源的规则.
        //限制访问流量 qps<=1 超过限制 规则启动 ,不能访问资源,进入降级逻辑.
        //规则和资源需要绑定 一个资源可以绑定多种规则.
        //sentinel不同规则 都需要整理成list 规则类型也不同
        //限流规则 FlowRule 熔断规则 DegradeRule 其它规则
        List<FlowRule> flowRules=new ArrayList<>();
        //创建一个规则对象
        FlowRule flowRule=new FlowRule();
        //属性设置  规则条件具体的参数值
        //规则绑定资源
        flowRule.setResource("sayHello");
        //流控规则 提供一个限流方式 qps 并发
        flowRule.setGrade(1);//1 qps限流方式 0 并发限流方式
        //限流阈值 上限
        flowRule.setCount(1);
        //加载到当前sentinel进程中
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
    }
}
