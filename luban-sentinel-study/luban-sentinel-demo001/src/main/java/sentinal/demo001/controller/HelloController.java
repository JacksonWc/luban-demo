package sentinal.demo001.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sentinal.demo001.service.IHelloService;


@Slf4j
@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("/hello")
    public String sayHello(String name){

        //准备一个sentinel资源入口,进入sentinel统计责任链中
        Entry entry=null;
        try{
            //对entry赋值,赋值过程,会真正进入sentinel计算统计责任（就是判断是否符合规则）链中
            entry= SphU.entry("sayHello");
            String result=helloService.sayHello(name);
            return result;
        }catch (BlockException e){
            //这里就是降级
            log.info("当前资源sayHello受到了限制",e);
        }finally {
            //释放entry资源
            if(entry!=null){
                entry.exit();
            }
        }
        return "error";
    }

}
