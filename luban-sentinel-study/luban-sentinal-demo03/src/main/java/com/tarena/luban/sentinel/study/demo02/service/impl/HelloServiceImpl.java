package com.tarena.luban.sentinel.study.demo02.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tarena.luban.sentinel.study.demo02.handler.HelloBlockHandler;
import com.tarena.luban.sentinel.study.demo02.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {


    @SentinelResource(value="sayHello",blockHandler = "sayHelloBlock",
            blockHandlerClass = HelloBlockHandler.class)
    @Override
    public String sayHello(String name) {
        System.out.println("业务层调用");
        int a=1/0;
        try {
            Thread.sleep(110);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello sentinel i am "+name;
    }
}
